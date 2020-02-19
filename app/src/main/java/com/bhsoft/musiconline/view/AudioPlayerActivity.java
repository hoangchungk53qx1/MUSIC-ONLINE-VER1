package com.bhsoft.musiconline.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bhsoft.musiconline.R;
import com.bhsoft.musiconline.config.Constants;
import com.bhsoft.musiconline.model.Audio;
import com.bhsoft.musiconline.model.AudioEvent;
import com.bhsoft.musiconline.service.PlayerService;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;

public class AudioPlayerActivity extends AppCompatActivity {
    private CircleImageView imgAvatar;
    private SeekBar sbProgress;
    private ImageView imgPrevious, imgPlay, imgNext;
    private TextView txtName, txtSinger, tvAuthor;
    Handler handler = new Handler(); // xử lý roation (quay ) đĩa
    Audio audio = new Audio();
    AudioEvent audioEvent = new AudioEvent();
    private int count;
    private  boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        initview();
        initData();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        // nhận Audio từ HouseÁctivity
        audio = (Audio) getIntent().getSerializableExtra("audio");
        // gửi hình ảnh lên
        Glide.with(AudioPlayerActivity.this)// cần 1 cái context, như bên apdater từ có holder giữ
              .load(audio.getImage())
                .into(imgAvatar);
        txtName.setText(audio.getName());
        tvAuthor.setText("Nhạc sĩ: "+audio.getAuthor());
        txtSinger.setText("Ca sĩ: "+ audio.getSinger());
        EventBus.getDefault().post(Constants.TYPE_SERVICE_CHECK);


        // Hẹn giờ 1 giây sau, nếu Service không chạy thì mình sẽ chạy Service
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isRunning){ // ngược lại của false sẽ chạy
                    roationImageView.run();
                    Intent intent = new Intent(AudioPlayerActivity.this, PlayerService.class);
                    intent.putExtra("sendservice",audio.getLink());
                    startService(intent);

                }

            }
        },1000);


    }

private Runnable roationImageView =new Runnable() {

    @Override
    public void run() {
        imgAvatar.setRotation(count);
        count++;
        if(count ==361){

            count =0;
        }

handler.postDelayed(this,15);

    }
};



    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // Phương thức đăng ký này là phương thức nhận sự kiện tư service gửi qua
    // nếu để bên service thì là service nhận sự kiện mà activity gửi qua
    // để gửi sự kiên(dự liệu) qua thì dùng EventBus
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AudioEvent event) {
        // Nếu loại sự kiện nhận về báo là Service đang chạy thì
        // mình sẽ gửi lên Service đối tượng Audio để play nhạc
        if(event.getType()== Constants.TYPE_SERVICE_RUNNING){
            isRunning= true;
           audioEvent.setType(Constants.TYPE_SERVICE_PLAY_AUDIO);
            audioEvent.setAudio(audio.getLink());
            EventBus.getDefault().post(audioEvent);
            roationImageView.run();

        }


    }
    public void initview() {
        imgAvatar = findViewById(R.id.imgAvatar);
        sbProgress = findViewById(R.id.sbProgress);
        imgPrevious = findViewById(R.id.imgPrevious);
        imgPlay = findViewById(R.id.imgPlay);
        imgNext = findViewById(R.id.imgNext);
        txtName = findViewById(R.id.txtName);
        txtSinger = findViewById(R.id.txtSinger);
        tvAuthor = findViewById(R.id.tvAuthor);
    }
}

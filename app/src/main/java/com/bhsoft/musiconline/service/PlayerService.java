package com.bhsoft.musiconline.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bhsoft.musiconline.callback.OnPlayingListener;
import com.bhsoft.musiconline.config.Constants;
import com.bhsoft.musiconline.model.AudioEvent;
import com.bhsoft.musiconline.utils.AudioPlayerUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PlayerService extends Service implements OnPlayingListener {



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public PlayerService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

       // hàm onStartCommand() để thực hiện những hành động ở đây.Bạn muốn service làm gì
        // ném qua 1 cái link để phát bài hát dường dẫn từ thằng AudioPlayerUtils
        // dùng getStringExtra để nhận dữ liệu
        String path = intent.getStringExtra("sendservice");
       AudioPlayerUtils.getInstance().play(path,PlayerService.this);

        return super.onStartCommand(intent, flags, startId);
    }



    // Nhận sự kiện từ thằng AudioPlayerActivity ném qua
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AudioEvent event) {
        if(event.getType()== Constants.TYPE_SERVICE_CHECK){
            EventBus.getDefault().post(Constants.TYPE_SERVICE_RUNNING);
        }

//        else if(event.getType()== Constants.TYPE_SERVICE_PLAY_AUDIO){
//            AudioPlayerUtils.getInstance().play(event.getAudio(),this);
//        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onPlay() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onComplete() {

    }
}

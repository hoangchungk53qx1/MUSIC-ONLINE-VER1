package com.bhsoft.musiconline.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bhsoft.musiconline.R;
import com.bhsoft.musiconline.adapter.AudioAdapter;
import com.bhsoft.musiconline.model.Audio;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HouseActivity extends AppCompatActivity {
    private EditText searchAudio;
    private ImageView img_searchListMusic;
    private RecyclerView recyclerViewHouse;
    private AudioAdapter audioAdapter;

    private ArrayList<Audio> arrayAudio= new ArrayList<>(); // lưu tạm
    private ArrayList<Audio> arrayListSave = new ArrayList<>(); // lưu cả thằng arraylist
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initview();
        createDemo();
        creatRecyclerView();
       EventData();
    }

    private void EventData() {
        searchAudio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // when text change data. do your work everythings you want
                // khi mà người dùng xóa bỏ hết , edittext == null thì sẽ clear màn hình .
                // sau đó add danh sách arraySave đã lưu vào
                if(TextUtils.isEmpty(s)){
                    arrayAudio.clear();
                    //và sau đó add arrayListSave vao arrayAudio để hiển thị ra như bình thường
                    arrayAudio.addAll(arrayListSave);
                    // sau khi lưu danh sách thi audioApdaters sẽ được thông báo đã thay đổi dữ liệu
                    audioAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
// Xử lý khi nhấn thay đổi về tìm kiếm bài hát mới. nó sẽ clear danh sách bài hát cũ
// Kiểm tra cái edit text xem người dùng có "nhập" để tìm kiếm không ?
    // nếu tìm kiếm sẽ xử lý
    // luôn hoạt động để xem dữ liệu người nhập có thay đổi không?


    private void createDemo() {
        Audio audio = new Audio();
        audio.setName("Hơn cả yêu");
        audio.setSinger("Đức Phúc");
        audio.setAuthor("Đức Phúc");
        audio.setId(123);
        audio.setLink("https://data25.chiasenhac.com/downloads/2065/3/2064876-88c7fd9e/128/Hon%20Ca%20Yeu%20-%20Duc%20Phuc.mp3");
        audio.setImage("https://data.chiasenhac.com/data/cover/116/115822.jpg");
        audio.setDate(System.currentTimeMillis()-15000);


        Audio audio1 = new Audio();
        audio1.setName("Chân ái");
        audio1.setSinger("Orange; Khói");
        audio1.setId(1234);
        audio1.setAuthor("Châu Đăng Khoa");
        audio1.setLink("https://data25.chiasenhac.com/downloads/2067/3/2066820-36e8d370/128/Chan%20Ai%20-%20Orange_%20Khoi.mp3");
        audio1.setImage("https://data.chiasenhac.com/data/cover/117/116181.jpg");
        audio1.setDate(System.currentTimeMillis()-10000);

        Audio audio2 = new Audio();
        audio2.setName("Do for you");
        audio2.setSinger("ca sỹ :"+"B Ray; AMee; Masew");
        audio2.setAuthor("B Ray, Grey-D");
        audio2.setId(12345);
        audio2.setLink("https://data25.chiasenhac.com/downloads/2066/3/2065964-4d0f20c5/128/Do%20For%20Love%20-%20B%20Ray_%20AMee_%20Masew.mp3");
        audio2.setImage("https://data.chiasenhac.com/data/cover/117/116005.jpg");
        audio2.setDate(System.currentTimeMillis()+77000);

        Audio audio3 = new Audio();
        audio3.setName("Anh thanh niên");
        audio3.setSinger("ca sỹ :"+"HuyR");
        audio3.setAuthor("HuyR");
        audio3.setId(123456);
        audio3.setLink("https://data25.chiasenhac.com/downloads/2061/3/2060821-946b2223/128/Anh%20Thanh%20Nien%20-%20HuyR.mp3");
        audio3.setImage("https://data.chiasenhac.com/data/cover/116/115083.jpg");
        audio3.setDate(System.currentTimeMillis()-40000);

        Audio audio4 = new Audio();
        audio4.setName("Tướng Quân");
        audio4.setAuthor("Đình Dũng");
        audio4.setSinger("Nhật Phong");
        //audio4.setId("185847");
        audio4.setLink("https://data19.chiasenhac.com/downloads/2027/2/2026800-1f96ebf0/128/Tuong%20Quan%20-%20Nhat%20Phong.mp3");
        audio4.setImage("https://data.chiasenhac.com/data/cover/108/107676.jpg");
        audio4.setDate(System.currentTimeMillis() - 80000);




        arrayAudio.add(audio);
        arrayAudio.add(audio1);
        arrayAudio.add(audio2);
        arrayAudio.add(audio3);
        arrayAudio.add(audio4);

        arrayAudio.add(audio);
        arrayAudio.add(audio1);
        arrayAudio.add(audio2);
        arrayAudio.add(audio3);
        arrayAudio.add(audio4);


        arrayAudio.add(audio);
        arrayAudio.add(audio1);
        arrayAudio.add(audio2);
        arrayAudio.add(audio3);
        arrayAudio.add(audio4);

        arrayAudio.add(audio);
        arrayAudio.add(audio1);
        arrayAudio.add(audio2);
        arrayAudio.add(audio3);
        arrayAudio.add(audio4);

        arrayAudio.add(audio);
        arrayAudio.add(audio1);
        arrayAudio.add(audio2);
        arrayAudio.add(audio3);
        arrayAudio.add(audio4);


        // lúc này arrayListSave đã chứa toàn bộ data của arrayAudio
        // lúc tìm kiếm nếu khác null thì sẽ cập nhập adapter lại
        // adapter sẽ được cập nhập danh sách của thằng arrayistSave
        arrayListSave.addAll(arrayAudio);

    }

    private void creatRecyclerView() {
        recyclerViewHouse.setHasFixedSize(true);
        recyclerViewHouse.setLayoutManager(new LinearLayoutManager(HouseActivity.this));
        audioAdapter = new AudioAdapter(arrayAudio);
        recyclerViewHouse.setAdapter(audioAdapter);

    }

    private void initview() {
        searchAudio         = findViewById(R.id.searchAudio);
      //  img_searchListMusic = findViewById(R.id.img_searchListMusic);
        recyclerViewHouse   = findViewById(R.id.recyclerViewHouse);
    }



    public void setBntSearch(View view) {
    // khi user nhấn vao button tim kiếm
        // kiểm tra thấy button

        String inputs = searchAudio.getText().toString();

        if(!TextUtils.isEmpty(inputs)){  // kiểm tra thấy không rỗng
            // phải clear danh sách trước khi nhận 1 danh sách mới

            arrayAudio.clear();// clear toàn bộ danh sách arrayAudio (arraylist tạm) để duyệt for và add vào danh sách tạm đó
            for(int i= 0;i< arrayListSave.size();i++){
                if(arrayListSave.get(i).getName().toLowerCase().contains(inputs.toLowerCase())) {  // duyệt để tìm vị trí chứa trong arraysave
                    arrayAudio.add(arrayListSave.get(i));
                }

            }
            // thông báo cho apdapterd dữ liệu thay đổi
            audioAdapter.notifyDataSetChanged();
        }


    }
}

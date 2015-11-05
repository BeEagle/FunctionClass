package cchao.org.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import cchao.org.mylibrary.photo.multiimageshow.ImagePagerActivity;

/**
 * Created by chenchao on 15/11/5.
 */
public class MainActivity extends AppCompatActivity{

    private Intent intent;
    private ArrayList<String> urls;

    protected void onCreate(Bundle saveBundle){
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_main);

        urls = new ArrayList<>();
        urls.add("http://www.91danji.com/Upload/2013721/20137211325304155383.jpg");
        urls.add("http://img0.pcgames.com.cn/pcgames/1509/23/5575660_4.jpg");
        urls.add("http://i2.17173.itc.cn/2011/news/2011/11/17/11_11171147_01s.jpg");
        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePagerActivity.openImagePaper(MainActivity.this, 0, urls);
            }
        });
    }
}

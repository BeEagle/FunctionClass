package cchao.org.testapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import cchao.org.mylibrary.photo.imageconversion.ImageCompress;
import cchao.org.mylibrary.photo.multiimageshow.ImagePagerActivity;

/**
 * Created by chenchao on 15/11/5.
 */
public class MainActivity extends AppCompatActivity{

    //拍照存储图片本地地址
    public Uri cameraFile = null;
    //跳转向拍照页面requestCode
    private static final int PICK_FROM_CAMERA = 1;

    private Intent intent;
    private ArrayList<String> urls;

    private ImageCompress imageCompress;
    private ImageView imageView;

    protected void onCreate(Bundle saveBundle){
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_main);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");

        imageView = (ImageView) findViewById(R.id.camera_image);

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

        this.findViewById(R.id.button_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraFile = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg"));
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, cameraFile);
                startActivityForResult(intent, PICK_FROM_CAMERA);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FROM_CAMERA && resultCode == -1){
            imageCompress = new ImageCompress(cameraFile.getPath(), 100);
            imageView.setImageBitmap(imageCompress.getSmallBitmap(480, 800));
        }
    }
}

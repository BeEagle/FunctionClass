package cchao.org.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import cchao.org.mylibrary.photo.localimage.LocalImagesUri;
import cchao.org.mylibrary.photo.localimage.util.ImageItem;

/**
 * Created by chenchao on 15/11/5.
 */
public class MainActivity extends AppCompatActivity{

    private List<ImageItem> mImageItem;

    protected void onCreate(Bundle saveBundle){
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_main);

        mImageItem = LocalImagesUri.getLocalImagesUri(this);
        for (ImageItem imageItem : mImageItem) {
            Log.i("test", imageItem.getImagePath());
        }
    }
}

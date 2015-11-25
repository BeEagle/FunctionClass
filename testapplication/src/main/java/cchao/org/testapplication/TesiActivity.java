package cchao.org.testapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenchao on 15/11/12.
 */
public class TesiActivity extends Activity {
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button_camera)
    Button buttonCamera;
    @Bind(R.id.camera_image)
    ImageView cameraImage;

    protected void onCreate(Bundle saveBundle) {
        super.onCreate(saveBundle);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}

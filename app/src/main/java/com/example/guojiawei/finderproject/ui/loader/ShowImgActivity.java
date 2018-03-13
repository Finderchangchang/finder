package com.example.guojiawei.finderproject.ui.loader;

import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.mapapi.model.LatLng;
import com.cjt2325.cameralibrary.TypeButton;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.example.guojiawei.finderproject.R;
import com.example.guojiawei.finderproject.ui.activity.EditorActivity;
import com.example.guojiawei.finderproject.util.Constant;
import com.example.guojiawei.finderproject.widget.camera.VideoAcitvity;

import java.io.File;
import java.io.IOException;

/**
 * 选择完图片以后，展示当前选择的图片
 */
public class ShowImgActivity extends AppCompatActivity {
    String url = "";//当前图片地址
    double output1;
    double output2;
    ImageView img;

    private TypeButton btn_confirm;         //确认按钮
    private TypeButton btn_cancle;         //取消按钮

    private int button_size;
    private LinearLayout left_ll;
    private LinearLayout right_ll;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        left_ll = (LinearLayout) findViewById(R.id.left_ll);
        right_ll = (LinearLayout) findViewById(R.id.right_ll);
        url = getIntent().getStringExtra("url");
        img = (ImageView) findViewById(R.id.img);
//        latLng = load_lat_lng(url);//获得当前图片的路径
        img.setImageURI(Uri.fromFile(new File(url)));
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        button_size = screenWidth;

        btn_confirm = new TypeButton(getBaseContext(), TypeButton.TYPE_CANCEL, (int) (button_size / 2f));
        btn_confirm.setBackgroundResource(R.drawable.ic_camera_cancel);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        left_ll.addView(btn_confirm);
        btn_cancle = new TypeButton(getBaseContext(), TypeButton.TYPE_CONFIRM, (int) (button_size / 2f));
        btn_cancle.setBackgroundResource(R.drawable.ic_camera_true);
        //确定按钮
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoAcitvity.main.finish();
                startActivity(new Intent(ShowImgActivity.this, EditorActivity.class)
                        .putExtra(Constant.TAG_LAT, latLng.latitude)
                        .putExtra(Constant.TAG_LON, latLng.longitude)
                        .putExtra("path", "")
                        .putExtra("url", url)
                        .putExtra("filename", ""));
                finish();
            }
        });
        right_ll.addView(btn_cancle);
    }


}

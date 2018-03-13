package com.example.guojiawei.finderproject.ui.loader;

import android.content.Context;
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
import com.example.guojiawei.finderproject.R;

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
    private RelativeLayout total_rl;
    private LinearLayout left_ll;
    private LinearLayout right_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);
        total_rl = (RelativeLayout) findViewById(R.id.total_rl);
        left_ll = (LinearLayout) findViewById(R.id.left_ll);
        right_ll = (LinearLayout) findViewById(R.id.right_ll);
        url = getIntent().getStringExtra("url");
        img = (ImageView) findViewById(R.id.img);
        LatLng latLng = load_lat_lng();//获得当前图片的路径
        img.setImageURI(Uri.fromFile(new File(url)));
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        button_size = screenWidth;

        btn_confirm = new TypeButton(getBaseContext(), TypeButton.TYPE_CONFIRM, (int) (button_size / 1.6f));
        btn_confirm.setBackgroundResource(R.drawable.ic_camera_true);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        left_ll.addView(btn_confirm);
        btn_cancle = new TypeButton(getBaseContext(), TypeButton.TYPE_CONFIRM, (int) (button_size / 1.6f));
        btn_cancle.setBackgroundResource(R.drawable.ic_camera_true);

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        right_ll.addView(btn_cancle);
    }

    private LatLng load_lat_lng() {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(url);
        } catch (IOException e) {
            finish();
        }
        String datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);// 拍摄时间
        String deviceName = exifInterface.getAttribute(ExifInterface.TAG_MAKE);// 设备品牌
        String deviceModel = exifInterface.getAttribute(ExifInterface.TAG_MODEL); // 设备型号
        String latValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
        String lngValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
        String latRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
        String lngRef = exifInterface.getAttribute
                (ExifInterface.TAG_GPS_LONGITUDE_REF);
        if (latValue != null && latRef != null && lngValue != null && lngRef != null) {
            try {
                output1 = convertRationalLatLonToFloat(latValue, latRef);
                output2 = convertRationalLatLonToFloat(lngValue, lngRef);
            } catch (IllegalArgumentException e) {
                finish();
            }
        }
        return new LatLng(output1, output1);
    }

    private static float convertRationalLatLonToFloat(
            String rationalString, String ref) {

        String[] parts = rationalString.split(",");

        String[] pair;
        pair = parts[0].split("/");
        double degrees = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        pair = parts[1].split("/");
        double minutes = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        pair = parts[2].split("/");
        double seconds = Double.parseDouble(pair[0].trim())
                / Double.parseDouble(pair[1].trim());

        double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
        if ((ref.equals("S") || ref.equals("W"))) {
            return (float) -result;
        }
        return (float) result;
    }
}

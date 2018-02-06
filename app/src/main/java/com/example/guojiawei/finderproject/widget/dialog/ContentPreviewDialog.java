package com.example.guojiawei.finderproject.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guojiawei.finderproject.R;

/**
 * Created by guojiawei on 2017/11/28.
 */

public class ContentPreviewDialog {
    private Dialog dialog;
    private Context context;
    private TextView tv;
    private GestureDetector gestureDetector;

    public ContentPreviewDialog(Context context, String str) {
        this.context = context;
        init(str);
    }

    private void init(String str) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_content_preview, null);
        tv = (TextView) view.findViewById(R.id.content);


        tv.setText(str);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        dialog = new Dialog(context, R.style.PreviewDialogStyle);

        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
        dialog.setContentView(view);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        window.setAttributes(lp);

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                dialog.dismiss();
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

    }

    public void show() {
        dialog.show();
    }

    public void dimiss() {
        dialog.dismiss();
    }
}

package com.example.guojiawei.finderproject.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.guojiawei.finderproject.R;

/**
 * Created by guojiawei on 2017/11/9.
 */

public class DialogSelector implements View.OnClickListener {

    TextView sure;
    TextView cancel;
    private Dialog dialog;

    private Context context;

    private String title;

    private DialogSelectorListener listener;


    public DialogSelector(Context context) {
        this.context = context;
        init();
        setStyle();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);

        sure = (TextView) view.findViewById(R.id.sure);
        cancel = (TextView) view.findViewById(R.id.cancel);

        sure.setOnClickListener(this);
        cancel.setOnClickListener(this);

        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);


    }

    private void setStyle() {
        // 设置dialog窗口样式
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure:
                if (listener != null) {
                    listener.onSure();
                    dismiss();
                }
                break;
            case R.id.cancel:
                if (listener != null) {
                    listener.onCancel();
                    dismiss();
                }
                break;
        }
    }
    public DialogSelector show() {
        dialog.show();
        return this;
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public DialogSelector setTitle(String title) {
        this.title = title;
        sure.setText(title);
        return this;
    }

    public DialogSelector setListener(DialogSelectorListener listener) {
        this.listener = listener;
        return this;
    }
}

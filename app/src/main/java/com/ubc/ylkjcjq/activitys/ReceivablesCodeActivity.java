package com.ubc.ylkjcjq.activitys;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ubc.ylkjcjq.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import es.dmoral.toasty.Toasty;

public class ReceivablesCodeActivity extends AutoLayoutActivity implements View.OnClickListener{

    private TextView tvCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivables_code);

        tvCode = (TextView)findViewById(R.id.tvCode);
        ImageView imgcode = (ImageView)findViewById(R.id.imgcode);


        Bitmap item = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_defailt);

        Bitmap bitmap = EncodingUtils.createQRCode("0x91654eaf7828e7e05d2847aa27405d9925c3dc0d", 500, 500, item);

        // 设置图片
        imgcode.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;

            case R.id.fuzhi:

                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvCode.getText());
                Toasty.success(this,"复制成功", Toast.LENGTH_SHORT,true).show();
                break;
            default:

                break;
        }
    }
}

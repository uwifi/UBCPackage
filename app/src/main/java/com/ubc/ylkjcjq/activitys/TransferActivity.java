package com.ubc.ylkjcjq.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ubc.ylkjcjq.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhy.autolayout.AutoLayoutActivity;

public class TransferActivity extends AutoLayoutActivity implements View.OnClickListener {

    private EditText mETAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        mETAddress = (EditText)findViewById(R.id.mETAddress);


        findViewById(R.id.iv_saosao).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String code = bundle.getString("code");
            mETAddress.setText(code);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.btn_shoukuan:
                startActivity(new Intent(this,ReceivablesCodeActivity.class));
                break;
            case R.id.iv_saosao:
                startActivityForResult(new Intent(this, CaptureActivity.class), 0);
                break;
            default:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            mETAddress.setText(result);
        }
    }
}

package com.fm.openinstalldemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fm.openinstall.OpenInstall;

public class ChannelActivity extends AppCompatActivity {

    Toolbar toolbar;
    public static final String POINT_ID = "effect_test";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("渠道统计");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.iv_expain).setBackgroundResource(R.drawable.expain_channel);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册上报
                OpenInstall.reportRegister();
                showReportDialog();
            }
        });

        findViewById(R.id.effect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //效果点统计
                OpenInstall.reportEffectPoint(POINT_ID, 1);
                showReportDialog();
            }
        });

    }

    private void showReportDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("数据已提交");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }


}
////
//load3-1.zip	(application/x-zip-compressed) - 1171605bytes
//        MD5	2c32df8da6d80abf71afb803dd7bef5b
//        SHA1	27405e36763827266cedfd1e6d49482418b8be75
//        SHA256	79e25f65daf08ca0ed039e78244d4ee964a70f8784e2d5eb2882e043c49a56cf
//
//
//        load3-2.zip	(application/x-zip-compressed) - 1171605bytes
//        MD5	df056f82af02657f0547b57fb1e370e7
//        SHA1	901025a51d168870f8e24ae27f3a9a8dc3594d97
//        SHA256	18c0ac45d23dd076898aa3ce73af74e7db6fdce6f0ace3a92daea82503083cd7
//
////
//        openinstall	(n/a) - 20bytes
//        MD5	0e57b7e3c8675c94ff98a1a2ab8663a5
//        SHA1	97b15b0cb64e1c003304a5ff34a863eed655aad1
//        SHA256	51b1e103a72b834dc011aed9f331c86c4686f590619b312a7c06fff841947832
//
//
//        openinstall	(n/a) - 20bytes
//        MD5	0e57b7e3c8675c94ff98a1a2ab8663a5
//        SHA1	97b15b0cb64e1c003304a5ff34a863eed655aad1
//        SHA256	51b1e103a72b834dc011aed9f331c86c4686f590619b312a7c06fff841947832
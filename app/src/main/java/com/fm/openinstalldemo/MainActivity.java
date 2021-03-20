package com.fm.openinstalldemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallAdapter;
import com.fm.openinstall.listener.AppWakeUpAdapter;
import com.fm.openinstall.model.AppData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        findViewById(R.id.install).setOnClickListener(this);
        findViewById(R.id.channel).setOnClickListener(this);
        findViewById(R.id.wakeup).setOnClickListener(this);


        // 确保用户同意《隐私政策》之后，再初始化openinstall SDK；如果用户不同意，则不进行openinstall SDK初始化
        OpenInstall.init(this);
        //获取唤醒参数
        OpenInstall.getWakeUp(getIntent(), wakeUpAdapter);

        final SharedPreferences sp = getSharedPreferences("openinstalldemo", MODE_PRIVATE);
        boolean needInstall = sp.getBoolean("needInstall", true);
        if (needInstall) {  //是否是第一次启动
            //获取OpenInstall数据，推荐每次需要的时候调用，而不是自己保存数据
            OpenInstall.getInstall(new AppInstallAdapter() {
                @Override
                public void onInstall(AppData appData) {
                    //获取渠道数据
                    String channelCode = appData.getChannel();
                    //获取个性化安装数据
                    String bindData = appData.getData();

                    if (!appData.isEmpty()) {
                        showInstallDialog(appData.toString());
                    }
                }
            });
            //只有第一次启动App才去获取，以后将不再获取：将needInstall设置为false
            sp.edit().putBoolean("needInstall", false).apply();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        /**
         * 此处要调用，否则App在后台运行时，会无法截获
         */
        OpenInstall.getWakeUp(intent, wakeUpAdapter);
    }

    /**
     * 唤醒参数获取回调
     * 如果在没有数据时有特殊的需求，可将AppWakeUpAdapter替换成AppWakeUpListener
     *
     * @param appData
     */
    AppWakeUpAdapter wakeUpAdapter = new AppWakeUpAdapter() {
        @Override
        public void onWakeUp(AppData appData) {
            //获取渠道数据
            String channelCode = appData.getChannel();
            //获取自定义数据
            String bindData = appData.getData();

            showWakeUpDialog(appData.toString());

        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.install:
                intent.setClass(this, InstallActivity.class);
                break;
            case R.id.channel:
                intent.setClass(this, ChannelActivity.class);
                break;
            case R.id.wakeup:
                intent.setClass(this, WakeupActivity.class);
                break;
        }
        startActivity(intent);
    }

    private void showInstallDialog(String data) {
        if (TextUtils.isEmpty(data)) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("OpenInstall");
        builder.setMessage("我是来自那个集成了 openinstall JS SDK 页面的安装，请根据你的需求" +
                "将我计入统计数据或是根据贵公司App的业务流程处理（如免填邀请码建立邀请关系、自动" +
                "加好友、自动进入某个群组或房间等）\n" + data);
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

    private void showWakeUpDialog(String data) {
        if (TextUtils.isEmpty(data)) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("OpenInstall");
        builder.setMessage("这是App被拉起获取的数据\n" + data);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wakeUpAdapter = null;
    }
}

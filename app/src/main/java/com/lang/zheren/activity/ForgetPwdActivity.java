package com.lang.zheren.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lang.zheren.R;
import com.lang.zheren.util.StreamTools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ForgetPwdActivity extends Activity {

    private EditText et_phone;
    protected static final int ERROR = 2;
    protected static final int SUCCESS = 1;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Toast.makeText(ForgetPwdActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(ForgetPwdActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        et_phone = (EditText) findViewById(R.id.et_phone);
    }

    public void btn_ok(View view) {
        final String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread() {
            public void run() {
                try {
                    String path = "http://192.168.1.105:80/xampp/forget.php";
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0(compatible;MSIE 9.0;Windows NT 6.1;Trident/5.0)");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//请求的类型  表单数据
                    String data = "phone=" + phone + "&button=";
                    conn.setRequestProperty("Content-Length", data.length() + "");//数据的长度
                    conn.setDoOutput(true);//设置向服务器写数据
                    byte[] bytes = data.getBytes();
                    conn.getOutputStream().write(bytes);//把数据以流的方式写给服务
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        InputStream is = conn.getInputStream();
                        String result = StreamTools.readStream(is);
                        Message mas = Message.obtain();
                        mas.what = SUCCESS;
                        mas.obj = result;
                        handler.sendMessage(mas);

                    } else {
                        Message mas = Message.obtain();
                        mas.what = ERROR;
                        handler.sendMessage(mas);
                    }
                } catch (Exception e) {
                    Message mas = Message.obtain();
                    mas.what = ERROR;
                    handler.sendMessage(mas);
                }
            }

            ;
        }.start();
    }

}

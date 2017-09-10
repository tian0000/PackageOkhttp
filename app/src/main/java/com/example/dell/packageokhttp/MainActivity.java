package com.example.dell.packageokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.dell.packageokhttp.bean.TestBean;
import com.example.dell.packageokhttp.response.GsonIResponse;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        PageOkHttpClient client = PageOkHttpClient.getInstance();
        client.get()
                .url("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi")
                .params("scope", "103")
                .params("format", "json")
                .params("appid", "379020")
                .params("bk_key", "小猫")
                .params("bk_length", "600")
                .enqueue(new GsonIResponse<TestBean>() {
                    @Override
                    protected void onSyccess(TestBean response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

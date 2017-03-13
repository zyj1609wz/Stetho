package com.zyj.stetho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zyj.stetho.bean.TestModel;
import com.zyj.stetho.util.DatabaseManager;
import com.zyj.stetho.util.SPUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editText ;
    private Button put_bt ;
    private Button get_bt ;
    private TextView get_tv ;
    private Button net_bt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById( R.id.et );
        put_bt = (Button) findViewById( R.id.put_bt );
        get_bt = (Button) findViewById( R.id.get_bt );
        get_tv = (TextView) findViewById( R.id.get_tv );
        net_bt = (Button) findViewById( R.id.net_bt );

        put_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put( MainActivity.this , "data" , editText.getText().toString() );
            }
        });

        get_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = (String) SPUtils.get( MainActivity.this , "data" , "" );
                get_tv.setText(  data + "" );
            }
        });

        //数据库操作
        db();

        //网络操作
        net_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        net();
                    }
                }).start();
            }
        });
    }

    void db(){
        List<TestModel> list = new ArrayList<>() ;
        for ( int i = 0 ; i < 10 ; i++ ){
            TestModel testModel = new TestModel() ;
            testModel.setId( "00" + i );
            testModel.setName( "zhaoyanjun" + i );
            testModel.setPassword( "123456" + i );

            list.add( testModel ) ;
        }

        DatabaseManager.getInstance().insertAll( list );
    }

    void net(){
        String url = "https://www.baidu.com/" ;

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor( new StethoInterceptor())  //添加拦截器
                .build() ;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if ( response.isSuccessful() ) {
                String result = response.body().string() ;
                Log.e( "zhao", "net: " + result );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

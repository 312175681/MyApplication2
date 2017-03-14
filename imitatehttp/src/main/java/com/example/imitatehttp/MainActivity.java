package com.example.imitatehttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
private Button btn;
    private TextView tv;
    private String host="cloud.bmob.cn";
    private int port=80;
    private  String masthead="cloud.bmob.cn/c284de47021c899d/getMemberBySex\r\nHost:cloud.bmob.cn\r\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.btn);
        tv=(TextView) findViewById(R.id.tv);
        btn.setOnClickListener(new ButtonClickListener());
    }
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn:
                    scketText();
                    break;
                default:
                    break;


            }
        }
    }

    private void scketText() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket=new Socket(host,port);
                    OutputStream ops =socket.getOutputStream();
                    OutputStreamWriter writer = new OutputStreamWriter(ops);
                    BufferedWriter bw=new BufferedWriter(writer);
                    bw.write(masthead);
                    bw.flush();
                    InputStream is=socket.getInputStream();
                    InputStreamReader isr= new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(isr);
                    StringBuffer result=new StringBuffer();
                    String line="";
                    while((line=br.readLine()!=bull)){
                        result.append(line);
                        Log.e("TAG",line);

                    }
                    socket.close();
                 } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

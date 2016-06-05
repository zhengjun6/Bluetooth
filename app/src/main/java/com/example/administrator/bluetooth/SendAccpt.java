package com.example.administrator.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class SendAccpt extends AppCompatActivity {
    private Button b;
    private EditText e;
    private TextView t;
    private Threa threa;
    private ArrayList<Info> list;
    private String str="";

public Handler h=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        String s= (String) msg.obj;
       // str=str+s;
   t.append(s+"\n");
    }
};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.accept);

        t=(TextView)findViewById(R.id.textView2);
        b=(Button)findViewById(R.id.button3);
        e=(EditText)findViewById(R.id.editText);

//            threa=new Threa();
   final   thread t=new thread(h,true);
b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //thread t=new thread(e.getText().toString());
        if(e.getText().toString().trim().length()>0){
        t.write(e.getText().toString());
        Message m=new Message();
        m.obj="我发送的:"+e.getText().toString();
        h.sendMessage(m);}
        else{
            Toast.makeText(SendAccpt.this,"请输入正确的数据",Toast.LENGTH_SHORT).show();
        }
        e.setText("");
    }
});




    }
}

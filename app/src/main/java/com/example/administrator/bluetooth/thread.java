package com.example.administrator.bluetooth;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.util.Xml;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Administrator on 2016/6/3.
 */
public class thread extends  Thread {
    private BluetoothSocket tmp;
    private BluetoothServerSocket sock;
    private static OutputStream outStream ;
    private InputStream inStream = null;
    private static boolean flag=false;
    private static Handler h;
    private String s;
    public thread(String s){
        this.s=s;
    }
    public thread(Handler h,boolean flag){
        this.h=h;
        this.flag=flag;
        Log.d("gg",""+flag);

    }
    public thread(BluetoothSocket tmp){
       this.tmp=tmp;
        this.h=h;
    }

    @Override
    public void run() {

        while (true) {
            Log.d("ff","123");
            while (flag) {

                try {
                    outStream = tmp.getOutputStream();
                    inStream = tmp.getInputStream();
                    byte b[] = new byte[1024];

                    Log.e("recv", "zijie:" + inStream.available());
                    int len, count = 0;


                    while ((len = inStream.read(b)) > 0) {
                        String str = new String(b, "ISO-8859-1");//将电脑数据发送给手机蓝牙客户端

                        str = str.substring(0, len);
                        Log.e("recv", "gg");


                        Message m = new Message();
                        m.obj = str;
                        h.sendMessage(m);

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("123", "fail");
                }


            }
        }
    }

   public void  write(String s){
       try {

           outStream.write(s.getBytes());//发送数据给电脑的蓝牙端
           outStream.flush();
       } catch (IOException e) {
           e.printStackTrace();
       }
       // new String(outStream.toString().toCharArray());

       Log.d("123", new String(outStream.toString().toCharArray()));

   }

}

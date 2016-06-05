package com.example.administrator.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by Administrator on 2016/6/1.
 */
public class Threa extends  Thread {
    private BluetoothDevice device;
 private BluetoothAdapter adapter;
   public static BluetoothSocket tmp = null;
    private BluetoothServerSocket socket=null;
    private Context context;
    private String Mac;


            public Threa(BluetoothDevice device, BluetoothAdapter adapter){

        this.device=device;
        this.Mac=Mac;
       this.adapter=adapter;
        //// 连接建立之前的先配对
        Method creMethod = null;
        try {
            creMethod = BluetoothDevice.class.getMethod("createBond");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            creMethod.invoke(device);//进行设备配对
        } catch (IllegalAccessException e) {
            Log.e("TAG", "开始配对");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//00001105-0000-1000-8000-00805f9b34fb
        //00001101-0000-1000-8000-00805F9B34FB
        try {
            tmp=device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));//进行uiid配对
            //socket=adapter.listenUsingRfcommWithServiceRecord(Mac,UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            Log.d("gg","uuid");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("gg","uuidfail");
        }
    }

    @Override
    public void run() {

        adapter.cancelDiscovery();
        try {
            tmp.connect();
            Log.d("gg","succ1");

         thread t=new thread(tmp);
            t.start();


           // Toast.makeText(context,"连接成功",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("gg","12344");
            try {
                tmp.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

}

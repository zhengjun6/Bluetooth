package com.example.administrator.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 这次是基于蓝牙串口通信写的蓝牙程序，
 * 所以普通手机蓝牙只能配对而不能连接，
 * 只有蓝牙模块才能连接
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private Button b,bsearch;
    private  BluetoothAdapter adapter;
    private Set<BluetoothDevice> pairedDevices;
    private ListView lv;
    private   List<Info> list=new ArrayList();;
    private Info info;
    private BluetoothSocket tmp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button);
        bsearch=(Button)findViewById(R.id.button2);
        lv=(ListView)findViewById(R.id.listView);
        lv.setOnItemClickListener(this);
     b.setOnClickListener(this);
        bsearch.setOnClickListener(this);
//        Intent searchIntent = new Intent(this, ComminuteActivity.class);
//        startActivity(searchIntent);
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
              open();
                break;
            case R.id.button2:
                search();
                break;
        }
    }
    private void open(){

        if (!adapter.isEnabled()) {
            adapter.enable();
        }
        Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);//打开蓝牙
        enable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300); //3600为蓝牙设备可见时间
        startActivity(enable);
    }

private void search(){
    list.clear();
    adapter.cancelDiscovery();
 lv.setVisibility(View.GONE);

    pairedDevices=adapter.getBondedDevices();//查看以前已经配对过的蓝牙设备的名字及其他信息

//    for(BluetoothDevice bt : pairedDevices){
//        info=new Info(bt.getName(),bt.getAddress(),bt);
//        list.add(info);}

    adapter.startDiscovery();// 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//    filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//    filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
//    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
    MainActivity.this.registerReceiver(broadcas, filter);


    //unregisterReceiver(broadcast);//关闭广播
}
    private BroadcastReceiver broadcas=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this,"123",Toast.LENGTH_SHORT).show();
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)|| BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)
                    ||BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)) {
                // Get the BluetoothDevice object from the Intent
               final  BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                Log.d("haha",device.getName()+device.getAddress());

                info=new Info(device.getName(),device.getAddress(),device);
                list.add(info);
            }
           Toast.makeText(MainActivity.this,"123",Toast.LENGTH_SHORT).show();
//            Log.d("haha","123");
            Adapter a=new Adapter(MainActivity.this,list);
            lv.setAdapter(a);
            lv.setVisibility(View.VISIBLE);
            //adapter.cancelDiscovery();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //list.get(position).equalsIgnoreCase()ada
        BluetoothSocket socket;
        String uuidValue="00001101-0000-1000-8000-00805F9B34FB";



    Threa t = new Threa( list.get(position).getDevice(), adapter);
    //Threa t = new Threa(tmp);
    t.start();

        Intent intent=new Intent(this,SendAccpt.class);


        startActivity(intent);
    // socket = list.get(position).getDevice().createRfcommSocketToServiceRecord(uuidValue);


    }


}

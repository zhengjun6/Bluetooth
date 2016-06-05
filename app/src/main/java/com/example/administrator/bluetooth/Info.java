package com.example.administrator.bluetooth;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Administrator on 2016/6/1.
 */
public class Info {
    private String name;
    private String mac;
    private BluetoothDevice device;

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public Info(String name, String mac,BluetoothDevice device) {
        this.name = name;
        this.mac = mac;
        this.device=device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}

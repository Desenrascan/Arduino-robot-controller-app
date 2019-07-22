package com.car_controller.robotcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {

    //Context
    private Context bluetoothBroadcastContext;

    //Bluetooth
    private BluetoothDevice broadcastBluetoothDevice;

    //ArrayList
    public ArrayList<String> deviceName;
    public ArrayList<String> deviceAddress;
    public ArrayList<BluetoothDevice> bluetoothDevices;

    //Variable
    private String getActionIntent;
    private String name;
    private String address;

    //Tag Variable
    private String unknownName = "Unknown";
    private String unknownAddress = "MAC address is unknown";

    //Constructor
    public BluetoothBroadcastReceiver(Context context) {
        this.bluetoothBroadcastContext = context;
        this.deviceName = new ArrayList<>();
        this.deviceAddress = new ArrayList<>();
        this.bluetoothDevices = new ArrayList<>();
    }

    @Override
    public void onReceive(Context broadcastContext, Intent broadcastIntent) {
        getActionIntent = broadcastIntent.getAction();

        if(broadcastBluetoothDevice.ACTION_FOUND.equals(getActionIntent)) {
            broadcastBluetoothDevice = broadcastIntent.getParcelableExtra(broadcastBluetoothDevice.EXTRA_DEVICE);
            name = broadcastBluetoothDevice.getName();
            address = broadcastBluetoothDevice.getAddress();

            //Check if discovered bluetooth device doesn't have required informations
            if(name.isEmpty() && address.isEmpty()) {
                name = unknownName;
                address = unknownAddress;
            }

            //Check for repeatness
            if(deviceName.size() == 0 && deviceAddress.size() == 0) {
                deviceName.add(name);
                deviceAddress.add(address);
                bluetoothDevices.add(broadcastBluetoothDevice);
            } else {

                for(int i = 0; i < deviceName.size(); i++) {
                    if(!(deviceName.get(i).equals(name))) {
                        deviceName.add(name);

                        //Add only one loop because name and address belongs to same device
                        bluetoothDevices.add(broadcastBluetoothDevice);
                    }
                }

                for(int i = 0; i < deviceAddress.size(); i++) {
                    if(!(deviceAddress.get(i).equals(address))) {
                        deviceAddress.add(address);
                    }
                }
            }
        }
    }
}

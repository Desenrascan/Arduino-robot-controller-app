package com.car_controller.robotcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;
import java.io.IOException;
import java.util.UUID;

public class ConnectToBluetoothDevice extends Thread {

    public static BluetoothSocket bluetoothSocket = null;
    private final BluetoothDevice bluetoothDevice;
    private final BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket tempBluetoothSocket;
    private CarControllerActivity carControllerActivity;
    private Context connectThreadContext;
    public boolean failed;

    private final String hc_06_UUID_String = "00001101-0000-1000-8000-00805F9B34FB";
    private final UUID hc_06_UUID = UUID.fromString(hc_06_UUID_String);

    public ConnectToBluetoothDevice(Context context, BluetoothAdapter adapter, BluetoothDevice device) {
        tempBluetoothSocket = null;
        bluetoothAdapter = adapter;
        bluetoothDevice = device;
        connectThreadContext = context;
        carControllerActivity = new CarControllerActivity();
        failed = false;

        try {
            tempBluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(hc_06_UUID);
            Toast.makeText(connectThreadContext, "Established RFCOMM channel", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(connectThreadContext, "RFCOMM channel creation failed" + e, Toast.LENGTH_SHORT).show();
            failed = true;
        }

        bluetoothSocket = tempBluetoothSocket;
    }

    public void run() {
        //Cancel discovering new devices
        if(bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }

        try {
            bluetoothSocket.connect();
            Toast.makeText(connectThreadContext, "Established connection with HC-06", Toast.LENGTH_SHORT).show();
        } catch (IOException connectionFailed) {
            Toast.makeText(connectThreadContext, "Couldn't establish connection " + connectionFailed,
                    Toast.LENGTH_SHORT).show();
            try {
                bluetoothSocket.close();
            } catch (IOException closeConnection) {
                Toast.makeText(connectThreadContext, "Couldn't close client socket " + closeConnection,
                        Toast.LENGTH_SHORT).show();
            }
            failed = true;
            return;
        }

        //Performing sending commands in a separate Thread
    }

    public void cancel() {
        try {
            bluetoothSocket.close();
        } catch (IOException closeConnection) {
            Toast.makeText(connectThreadContext, "Couldn't close client socket " + closeConnection,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static BluetoothSocket getBluetoothSocket() {
        return bluetoothSocket;
    }
}

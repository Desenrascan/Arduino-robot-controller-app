package com.car_controller.robotcontroller;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;

public class BluetoothTransferData extends Thread {

    private final BluetoothSocket bluetoothSocket;
    private BluetoothSocket tempBluetoothSocket;
    private Context bluetoothContext;
    private final OutputStream outputStream;
    private byte[] bluetoothBuffer;

    public BluetoothTransferData(Context context, BluetoothSocket socket) {
        bluetoothSocket = socket;
        bluetoothContext = context;
        OutputStream tempOutput = null;

        try {
            try {
                tempOutput = bluetoothSocket.getOutputStream();
            } catch (NullPointerException e){
                Toast.makeText(bluetoothContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException outputStream) {
            Toast.makeText(bluetoothContext, "Failed to register output stream", Toast.LENGTH_SHORT).show();
        }

        outputStream = tempOutput;
    }

    public void write(byte bytes) {
        try {
            outputStream.write(bytes); //Writes the specified byte to this output stream.
            Toast.makeText(bluetoothContext, bytes + " sent", Toast.LENGTH_SHORT).show();
        } catch (IOException writeFailed) {
            Toast.makeText(bluetoothContext, "Failed to write to remote device", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel() {
        try {
            bluetoothSocket.close();
        } catch (IOException closeConnection) {
            Toast.makeText(bluetoothContext, "Couldn't close client socket " + closeConnection, Toast.LENGTH_SHORT).show();
        }
    }
}

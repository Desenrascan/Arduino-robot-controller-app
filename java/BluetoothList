package com.car_controller.robotcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class BluetoothList extends RecyclerView.Adapter<BluetoothList.BluetoothDeviceHolder> {
    private ArrayList<String> deviceName;
    private ArrayList<String> deviceAddress;
    private ArrayList<BluetoothDevice> bluetoothDevices;
    private Context context;
    private BluetoothDialog bluetoothDialog;
    private BluetoothAdapter bluetoothAdapter;
    private CarControllerActivity carControllerActivity;
    private View viewText;
    private LayoutInflater inflater;
    private String connect = "Connecting...";

    public class BluetoothDeviceHolder extends RecyclerView.ViewHolder {

        public TextView deviceName;
        public TextView deviceAddress;
        public BluetoothDeviceHolder(View v) {
            super(v);
            deviceName = v.findViewById(R.id.deviceName);
            deviceAddress = v.findViewById(R.id.deviceAddress);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BluetoothList(BluetoothDialog dialog, Context context, BluetoothAdapter blAdapter, ArrayList<BluetoothDevice> devices, ArrayList<String> name, ArrayList<String> address) {
        this.bluetoothDialog = dialog;
        this.context = context;
        this.bluetoothDevices = devices;
        this.bluetoothAdapter = blAdapter;
        this.deviceName = name;
        this.deviceAddress = address;
        this.carControllerActivity = new CarControllerActivity();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BluetoothList.BluetoothDeviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        inflater = LayoutInflater.from(parent.getContext());
        viewText = inflater.inflate(R.layout.recyclerview_text, parent, false);

        return new BluetoothDeviceHolder(viewText);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BluetoothDeviceHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.deviceName.setText(deviceName.get(position));
        holder.deviceAddress.setText(deviceAddress.get(position));

        holder.deviceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, deviceAddress.getText(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, connect, Toast.LENGTH_SHORT).show();

                //Hand over clicked bluetooth device info to main activity function
                if(carControllerActivity != null) {
                    carControllerActivity.bluetoothInfo(context, bluetoothDialog, bluetoothDevices.get(position), bluetoothAdapter);
                }
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return deviceName.size();
    }
}

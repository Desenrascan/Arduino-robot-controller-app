package com.car_controller.robotcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class BluetoothDialog extends DialogFragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView searchMode;
    private View view;
    private Context context;
    private static BluetoothDialog bluetoothDialog;
    private BluetoothDialog dialogAdapter;

    private BluetoothBroadcastReceiver broadcastReceiver;
    private BluetoothAdapter blAdapter;
    //Intent to receive broadcast
    private IntentFilter intentFilter;

    private String foundMode = "Available devices";
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> dataSet = new ArrayList<>();

    public static BluetoothDialog newInstance() {
        bluetoothDialog = new BluetoothDialog();
//        bluetoothDialog.setCancelable(false);

        return bluetoothDialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize BroadcastRecevier Objects
        broadcastReceiver = new BluetoothBroadcastReceiver(context);
        blAdapter = BluetoothAdapter.getDefaultAdapter();
        intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        //Initialize BroadcastReceiver
        try {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
            Toast.makeText(context, "Res", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bluetooth, container, false);

        //Initialize RecyclerView Objects
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Initialize Wiew Objects
        progressBar = view.findViewById(R.id.progressBar2);
        recyclerView = view.findViewById(R.id.recyler);
        searchMode = view.findViewById(R.id.bluetoothSearchMode);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(blAdapter == null) {
            Toast.makeText(context, "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        } else if(!blAdapter.isEnabled()) {
            blAdapter.enable();
            Toast.makeText(context, "Bluetooth enabled", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Bluetooth already enabled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //Start Discovery
        if(blAdapter.isDiscovering()) {
            blAdapter.cancelDiscovery();
            blAdapter.startDiscovery();
            Toast.makeText(context, "Bluetooth discovering", Toast.LENGTH_SHORT).show();
        } else {
            blAdapter.startDiscovery();
            Toast.makeText(context, "Bluetooth discovering", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(broadcastReceiver.deviceName.size() != 0 && broadcastReceiver.deviceAddress.size() != 0) {
                    recyclerView.setAdapter(new BluetoothList(bluetoothDialog, context, blAdapter, broadcastReceiver.bluetoothDevices, broadcastReceiver.deviceName, broadcastReceiver.deviceAddress));
                    searchMode.setText(foundMode);
                } else {
                    Toast.makeText(context, "Couldn't discover, retry again", Toast.LENGTH_SHORT).show();
                    bluetoothDialog.dismiss();
                }

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }, 6000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(broadcastReceiver);
    }
}

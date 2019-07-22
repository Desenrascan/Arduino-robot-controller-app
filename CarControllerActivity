package com.car_controller.robotcontroller;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class CarControllerActivity extends AppCompatActivity {

    private boolean lightOn = false;
    private int carLight;

    private boolean controllerLocationRight = false;
    private boolean controllerLocationLeft = false;

    private ControllerLocationDialog controllerLocationDialogInstance;
    private DialogFragment locationDialog;

    private Toolbar mainToolbar;
    private MenuInflater menuInflater;
    private int toolbar_ID = R.id.mainToolbar;
    private int toolbarMenu = R.menu.main_toolbar;
    private int bluetoothIcon_ID = R.id.bluetooth;
    private int microphoneIcon_ID = R.id.microphone;

    private Button forwardButton = null;
    private Button backwardButton = null;
    private Button leftButton = null;
    private Button rightButton = null;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private BluetoothDialog bluetoothDialog;
    private RotateAlertDialog rotateAlertDialog;
    private BluetoothDialog mainBluetoothDialog;
    private ConnectToBluetoothDevice connectToBluetoothDevice;
    private BluetoothTransferData bluetoothTransferData;
    private BluetoothDevice mainBluetoothDevice;
    private BluetoothAdapter mainBluetoothAdapter;
    private BluetoothSocket mainBluetoothSocket;
    private Context mainBluetoothContext;

    //Commands to send over bluetooth
    private final byte FORWARD = 1;
    private final byte BACKWARD = 2;
    private final byte BACK = 2;
    private final byte RIGHT = 3;
    private final byte LEFT = 4;

    private ArrayList<String> speechRecognizerResult;
    private String speechRecognizerResultString;
    private int VOICE_SEARCH_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robot_controller_main_activity_right_side);

        setMainToolbar();
    }

    //Show searched bluetooth devices list
    void showDialog() {
        bluetoothDialog = BluetoothDialog.newInstance();
        bluetoothDialog.show(getSupportFragmentManager(), "bluetoothDialog");
    }

    //Show rotate dialog
//    void showRotateDialog() {
//        rotateAlertDialog = RotateAlertDialog.newRotateInstance();
//        rotateAlertDialog.show(getSupportFragmentManager(), "rotateDialog");
//    }

    //Show when user clicks on microphone icon
    void initializeSpeech() {
        if(mainBluetoothAdapter == null) {
            speechRecognizer = speechRecognizer.createSpeechRecognizer(getApplicationContext());

            if(speechRecognizer.isRecognitionAvailable(getApplicationContext())) {
                speechRecognizer.setRecognitionListener(new SpeechRecognitionListener());
                createSpeechRecognizerIntent();
                speechRecognizer.startListening(speechRecognizerIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Speech Recognition isn't avaliable", Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth not enabled", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    //Set main action bar
    private void setMainToolbar() {
        mainToolbar = findViewById(toolbar_ID);
        setSupportActionBar(mainToolbar);
    }

    //Set icons on action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        menuInflater.inflate(toolbarMenu, menu);
        return true;
    }

    //Choose which action to take on clicked action bar icon
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == bluetoothIcon_ID) {
            showDialog();
            return true;
        } else if(menuItem.getItemId() == microphoneIcon_ID) {
            initializeSpeech();
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }

    //Show user controller dialog to choose location of controller
    private void showControllerLocationDialog() {
        controllerLocationDialogInstance = ControllerLocationDialog.newInstance();
        controllerLocationDialogInstance.show(getSupportFragmentManager(), "controllerDialog");
    }

    //Changes car lights state on button press
    public void changeCarLight(View view) {
        if(lightOn) {
            lightOn = false;
            carLight = R.drawable.car_light_off;
        } else {
            lightOn = true;
            if(Build.VERSION.SDK_INT >= 23) {
                carLight = R.drawable.car_light_on_ripple;
            } else {
                carLight = R.drawable.car_light_on;
            }
        }
        view.setBackgroundResource(carLight);
    }

    //Get user choice to place direction controller
    public void controllerLocationRight(View view) {
        controllerLocationRight = true;
        locationDialog = ControllerLocationDialog.getControllerLocationDialogInstance();
        locationDialog.dismiss();
    }

    public void controllerLocationLeft(View view) {
        controllerLocationLeft = true;
        locationDialog = ControllerLocationDialog.getControllerLocationDialogInstance();
        locationDialog.dismiss();
    }

    public void bluetoothInfo(Context context, BluetoothDialog dialog, BluetoothDevice device, BluetoothAdapter adapter) {
        mainBluetoothContext = context;
        mainBluetoothDialog = dialog;
        mainBluetoothDevice = device;
        mainBluetoothAdapter = adapter;

        connectToBluetoothDevice = new ConnectToBluetoothDevice(mainBluetoothContext, mainBluetoothAdapter, mainBluetoothDevice);

        connectToBluetoothDevice.run();
        if(device == null) {
            connectToBluetoothDevice.cancel();
        }

        if(connectToBluetoothDevice.failed) {
            Toast.makeText(context, "Retry again", Toast.LENGTH_SHORT).show();
        } else {
            if(mainBluetoothDialog != null) {
                mainBluetoothDialog.dismiss();
            }
        }
    }

    private void createSpeechRecognizerIntent() {
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "Lang_Model");
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_RESULTS, "Extra_Results");

        try {
            startActivityForResult(speechRecognizerIntent, VOICE_SEARCH_ACTIVITY_REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == VOICE_SEARCH_ACTIVITY_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                speechRecognizerResult = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                speechRecognizerResultString = speechRecognizerResult.get(0);
                switch (speechRecognizerResultString) {
                    case "forward":
                        sendDataToRemoteDevice(FORWARD);
                        break;
                    case "back":
                        sendDataToRemoteDevice(BACK);
                        break;
                    case "right":
                        sendDataToRemoteDevice(RIGHT);
                        break;
                    case "left":
                        sendDataToRemoteDevice(LEFT);
                        break;
                }
            } else if(resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Couldn't get data", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

//    public void closeRotateDialog(View view) {
//        if(rotateAlertDialog != null) {
//            rotateAlertDialog.dismiss();
//        }
//    }

    public void buttonForwardColor(View view) {
        sendDataToRemoteDevice(FORWARD);
    }

    public void buttonLeftColor(View view) {
        sendDataToRemoteDevice(LEFT);
    }

    public void buttonBackwardColor(View view) {
        sendDataToRemoteDevice(BACKWARD);
    }

    public void buttonRightColor(View view) {
        sendDataToRemoteDevice(RIGHT);
    }

    private void sendDataToRemoteDevice(byte bytes) {
        mainBluetoothSocket = connectToBluetoothDevice.getBluetoothSocket();
        if(mainBluetoothSocket != null) {
            bluetoothTransferData = new BluetoothTransferData(getApplicationContext(), mainBluetoothSocket);
            Toast.makeText(getApplicationContext(), "Sending data to remote device", Toast.LENGTH_SHORT)
                    .show();
            bluetoothTransferData.write(bytes);
        } else {
            Log.d("Null", "Null socket");
        }
    }

    class SpeechRecognitionListener implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle parameters) { }

        @Override
        public void onRmsChanged(float rmsdB) { }

        @Override
        public void onBufferReceived(byte[] buffer) { }

        @Override
        public void onBeginningOfSpeech() { }

        @Override
        public void onEndOfSpeech() { }

        @Override
        public void onError(int error) { }

        @Override
        public void onPartialResults(Bundle partialResults) { }

        @Override
        public void onResults(Bundle results) { }

        @Override
        public void onEvent(int eventType, Bundle parameters) { }
    }
}

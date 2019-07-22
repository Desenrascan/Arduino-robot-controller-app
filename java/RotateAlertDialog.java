package com.car_controller.robotcontroller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RotateAlertDialog extends DialogFragment {

    private static RotateAlertDialog rotateDialog;
    private static Bundle rotateBundle;
    private View rotateDialogView;
    public static RotateAlertDialog newRotateInstance() {
        rotateDialog = new RotateAlertDialog();
        rotateDialog.setCancelable(false);
        rotateBundle = new Bundle();
        rotateBundle.putInt("Rotate", 0);

        return rotateDialog;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onCreate() {
        super.onCreate(rotateBundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rotateDialogView = inflater.inflate(R.layout.dialog, container, false);

        return rotateDialogView;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public static RotateAlertDialog getRotateObject() {
        return rotateDialog;
    }
}

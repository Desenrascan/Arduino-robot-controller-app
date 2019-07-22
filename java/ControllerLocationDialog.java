package com.car_controller.robotcontroller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ControllerLocationDialog extends DialogFragment {

    private static ControllerLocationDialog controllerLocationDialog;
    private static Bundle controllerLocationBundle;
    private View controllerLocationView;
    private static String bundleKey;
    private static String bundleValue = "controllerLocationDialog";
    private int dialogLayout = R.layout.controller_location_dialog;

    public static ControllerLocationDialog newInstance() {
        controllerLocationDialog = new ControllerLocationDialog();
        controllerLocationBundle = new Bundle();
        controllerLocationBundle.putString(bundleKey, bundleValue);

        controllerLocationDialog.setArguments(controllerLocationBundle);
        controllerLocationDialog.setCancelable(false);

        return controllerLocationDialog;
    }

    public void onAttach(Context context) { super.onAttach(context); }

    public void onCreate() { super.onCreate(controllerLocationBundle); }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup dialogContainer, Bundle savedInstanceState) {
        controllerLocationView = layoutInflater.inflate(dialogLayout, dialogContainer, false);
        return controllerLocationView;
    }

    public void onDestroy() { super.onDestroy(); }

    public static ControllerLocationDialog getControllerLocationDialogInstance() {
        return controllerLocationDialog;
    }
}

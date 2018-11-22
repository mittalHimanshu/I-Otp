package com.example.phoenix.i_otp;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.Manifest.permission.RECEIVE_SMS;

public class MainActivity extends AppCompatActivity {

    public static final int RequestPermissionCode = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (CheckingPermissionIsEnabledOrNot())
            startMain();

        else
            RequestMultiplePermission();


    }

    public void startMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Service Started Successfully", Toast.LENGTH_SHORT).show();
                MainActivity.this.finish();
            }
        }, 500);
    }

    public boolean CheckingPermissionIsEnabledOrNot() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), RECEIVE_SMS);
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED;

    }

    private void RequestMultiplePermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{RECEIVE_SMS}, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean SMSPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (SMSPermission)
                        startMain();
                    else
                        Toast.makeText(getApplicationContext(), "Please provide all the permissions", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}



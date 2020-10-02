package com.example.sensorlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    private SensorManager sensorManager ;

    private Sensor sensor ;
       private boolean flashCamera = false ;

    private Button btnFlash;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(),
                FlashLightService.class);
        startService(intent);






    }

    @Override
    public void onSensorChanged(final SensorEvent event){

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){

            if (event.values[0] == 8){
                flashOf();
            }else {
                flashOn();
            }


        }

    }



    private void flashOn(){
        CameraManager cameraManager =
                (CameraManager)getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
        }
    }



    private void flashOf() {

        CameraManager cameraManager =
                (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, false);
            }
        } catch (CameraAccessException e) {
        }
    }




    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

    }
}

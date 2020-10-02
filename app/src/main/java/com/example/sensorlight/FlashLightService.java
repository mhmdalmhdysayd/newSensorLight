package com.example.sensorlight;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.hardware.Sensor.*;

public class FlashLightService extends Service {

    /**
     * comment.
     */ private SensorManager sensorManager;
    private Sensor sensor;
    private Button btnFlash;
    private boolean flashCamera = false;

    @Override
    public IBinder onBind(final Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");    }


    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(TYPE_PROXIMITY) != null) {
            sensor = sensorManager.getDefaultSensor(TYPE_PROXIMITY);
        } else {
            Toast.makeText(this, "sensor not fond !!!", Toast.LENGTH_SHORT).show();
        }
        flashCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        return START_STICKY;

    }






    @Override
    public void onDestroy() {
        super.onDestroy();

}


}
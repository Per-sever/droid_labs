package com.example.laboratorywork9;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity3 extends AppCompatActivity implements
        SensorEventListener {
    TextView myText;
    SensorManager sensorManager;
    Sensor sensorAccel;
    float[] valuesAccel = new float[3];
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myText = findViewById(R.id.myText);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccel =
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAccel,
                SensorManager.SENSOR_DELAY_NORMAL);
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myText.setText("");
                        myText.append("Ускорение\nX: " + valuesAccel[0] +
                                "\nY: " + valuesAccel[1] + "\nZ: " + valuesAccel[2]);
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        timer.cancel();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    @Override
    public void onSensorChanged(SensorEvent event) {
        for (int i = 0; i < 3; i++){
            valuesAccel[i] = event.values[i];
        }
    }
}
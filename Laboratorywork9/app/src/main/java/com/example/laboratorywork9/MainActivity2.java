package com.example.laboratorywork9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements SensorEventListener  {
    private SensorManager sensorManager;
    private Sensor mLight;
    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myText = findViewById(R.id.myText);
        myText.setText("Значение освещенности в люксах:" + "\n");
        myText.setMovementMethod(new ScrollingMovementMethod());
        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Действия, если точность изменилась
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        myText.append(Float.toString(lux) + "\n");

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mLight,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}

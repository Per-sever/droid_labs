package com.example.laboratorywork9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SensorManager sensorManager;
    ScrollView sV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button detectorsBTN = findViewById(R.id.button3);
        Button ligthBTN = findViewById(R.id.light_btn);
        Button accelBTN = findViewById(R.id.accelerometer_btn);
        detectorsBTN.setOnClickListener(this);
        ligthBTN.setOnClickListener(this);
        ligthBTN.setOnClickListener(view -> openActivity2());
        accelBTN.setOnClickListener(view -> openActivity3());

        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);

    }

    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }

    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);

    }



    @Override
    public void onClick(View view) {
        TextView myText = findViewById(R.id.myText);

        switch (view.getId()) {
            case R.id.button3:
                myText.setText("");
                if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
                    myText.append("На устройстве есть гироскоп\n");
                } else {
                    myText.append("На устройстве нет гироскопа\n");
                }
                if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) !=
                        null) {
                    myText.append("На устройстве есть акселерометр\n");
                } else {
                    myText.append("На устройстве нет акселерометра\n");
                }
                if (sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION) !=
                        null) {
                    myText.append("На устройстве есть датчик\n" +
                            "обнаружения значимого движения для пробуждения устройства\n");
                } else {
                    myText.append("На устройстве нет датчика\n" +
                            "обнаружения значимого движения для пробуждения устройства\n");
                }
                if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) !=
                        null) {
                    myText.append("На устройстве есть датчик отслеживания шагов\n");
                } else {
                    myText.append("На устройстве нет  датчика отслеживания шагов\n");
                }
                if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) !=
                        null) {
                    myText.append("На устройстве есть датчик света\n");
                } else {
                    myText.append("На устройстве нет датчика света\n");
                }
                if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) !=
                        null) {
                    myText.append("На устройстве есть датчик магнитного поля\n");
                } else {
                    myText.append("На устройстве нет датчика магнитного поля\n");
                }
                myText.setMovementMethod(new ScrollingMovementMethod());
                break;
        }
    }
}

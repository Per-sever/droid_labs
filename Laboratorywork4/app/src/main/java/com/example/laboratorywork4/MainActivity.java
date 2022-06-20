package com.example.laboratorywork4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn2;
    private Button btn3;
    private Button btnLf;
    private TextView tV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn2.setOnClickListener(view -> openActivity2());
        btn3.setOnClickListener(view -> openActivity3());
        btnLf = (Button) findViewById(R.id.lifecycle_btn);
        tV = findViewById(R.id.textView2);
        btnLf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lifecycle.State lfStatus = getLifecycle().getCurrentState();
                tV.setText(lfStatus.toString());
            }
        });

    }

    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}
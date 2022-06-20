package com.example.laboratorywork5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnTouchListener {
    private GestureDetectorCompat myDetector;
    int upIndex = 0;
    int downIndex = 0;
    boolean touchFlag = false;
    TextView[] idView = new TextView[10];
    TextView[] xView = new TextView[10];
    TextView[] yView = new TextView[10];
    TextView myText;
    TextView myView;
    TextView scndView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.myView);
        myText = findViewById(R.id.myText);
        scndView = findViewById(R.id.secondView);
        String myPackage = getPackageName();
        Resources myResources = getResources();
        GestureListener myGestures = new GestureListener();
        myDetector = new GestureDetectorCompat(this, myGestures);
        for (int i = 0; i < 10; i++) {
            idView[i] = findViewById(myResources.getIdentifier("id" + i, "id",
                    myPackage));
            xView[i] = findViewById(myResources.getIdentifier("x" + i, "id",
                    myPackage));
            yView[i] = findViewById(myResources.getIdentifier("y" + i, "id",
                    myPackage));
        }
        myView.setOnTouchListener(this);
        scndView.setOnTouchListener(this);
    }

    public class GestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {

            idView[0].setText("On Down");
            xView[0].setText("-");
            yView[0].setText("-");

            return true;

        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

            idView[1].setText("On ShowPress");
            xView[1].setText("-");
            yView[1].setText("-");

        }


        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {

            idView[2].setText("On Single TapUp");
            xView[2].setText("-");
            yView[2].setText("-");

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

            idView[3].setText("On Scroll");
            xView[3].setText("-");
            yView[3].setText("-");

            return true;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {


            idView[4].setText("On Long Press");
            xView[4].setText("-");
            yView[4].setText("-");


        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            idView[5].setText("On Fling");
            xView[5].setText("-");
            yView[5].setText("-");
            return false;
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.secondView:
                return myDetector.onTouchEvent(event);
            case R.id.myView:
                int actionMask = event.getActionMasked();
                int pointerIndex = event.getActionIndex();
                int pointerCount = event.getPointerCount();
                switch (actionMask) {
                    case MotionEvent.ACTION_DOWN:
                        touchFlag = true;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        downIndex = pointerIndex;
                        break;
                    case MotionEvent.ACTION_UP:
                        touchFlag = false;
                        for (int i = 0; i < 10; i++) {
                            idView[i].setText("");
                            xView[i].setText("");
                            yView[i].setText("");
                            myText.setText("Координаты касаний");
                        }
                    case MotionEvent.ACTION_POINTER_UP:
                        upIndex = pointerIndex;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        for (int i = 0; i < 10; i++) {
                            idView[i].setText("");
                            xView[i].setText("");
                            yView[i].setText("");
                            if (i < pointerCount) {
                                idView[i].setText(Integer.toString(event.getPointerId(i)));
                                xView[i].setText(Integer.toString((int) event.getX(i)));
                                yView[i].setText(Integer.toString((int) event.getY(i)));
                            } else {
                                idView[i].setText("");
                                xView[i].setText("");
                                yView[i].setText("");
                            }
                        }
                        break;
                }

                if (touchFlag) {
                    myText.setText("Количество касаний: " + Integer.toString(pointerCount) + "\n" + "Индекс последнего касания: " +
                            downIndex + "\n" + "Индекс последнего отпускания: " + upIndex);
                }
                return true;
            default:
                return true;
        }


    }

}
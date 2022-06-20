package com.example.laboratorywork6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        ImageView figure = findViewById(R.id.figure);
        figure.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        figure.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
        TextView text = findViewById(R.id.title);


        switch (item.getItemId()) {
            case R.id.rectanglesd:
                text.setText("");
                float[] outR = new float[]{50, 50, 50, 50, 50, 50, 50, 50};
                RectF rectF = new RectF(40, 40, 40, 40);
                float[] inR = new float[]{10, 10, 10, 10, 10, 10, 10, 10};
                ShapeDrawable rect = new ShapeDrawable(new RoundRectShape(outR, rectF,
                        inR));
                rect.setIntrinsicHeight(300);
                rect.setIntrinsicWidth(500);
                rect.getPaint().setColor(Color.RED);

                figure.setBackground(rect);
                Animation animationRect = AnimationUtils.loadAnimation(this, R.anim.rotate);
                figure.startAnimation(animationRect);
                return true;
            case R.id.circlesd:
                text.setText("");
                ShapeDrawable circle = new ShapeDrawable(new ArcShape(0, 355));
                circle.setIntrinsicHeight(200);
                circle.setIntrinsicWidth(200);
                circle.getPaint().setColor(Color.BLACK);
                figure.setBackground(circle);
                Animation animationCircle = AnimationUtils.loadAnimation(this, R.anim.translate);
                figure.startAnimation(animationCircle);
                return true;
            case R.id.starsd:
                text.setText("");
                Path p = new Path();
                p.moveTo(50, 0);
                p.lineTo(10, 100);
                p.lineTo(110, 40);
                p.lineTo(0, 40);
                p.lineTo(100, 100);
                p.lineTo(50, 0);
                ShapeDrawable d = new ShapeDrawable(new PathShape(p, 120, 120));
                d.setIntrinsicHeight(200);
                d.setIntrinsicWidth(200);
                d.getPaint().setColor(Color.DKGRAY);
                d.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                figure.setBackground(d);
                Animation animationStar = AnimationUtils.loadAnimation(this, R.anim.scale);
                figure.startAnimation(animationStar);
                return true;
            case R.id.castanim:
                text.setText("");
                figure.clearAnimation();
                figure.getLayoutParams().height = (int) (1150 / getResources().getDisplayMetrics().density);
                figure.getLayoutParams().width = (int) (1600 / getResources().getDisplayMetrics().density);
                figure.setBackgroundResource(R.drawable.castanimation);
                AnimationDrawable animation = (AnimationDrawable) figure.getBackground();
                animation.start();
                return true;
            case R.id.sv:
                text.setText("");
                setContentView(new MySurfaceView(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
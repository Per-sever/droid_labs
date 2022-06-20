package com.example.laboratorywork1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private float mTextSize = 20;
    private EditText mEdit;
    private TextView tSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        mEdit = (EditText) findViewById(R.id.edit_text);
        tSize = (TextView) findViewById(R.id.size);
        tSize.setText(String.format("%.0f", mTextSize));
        ToggleButton buttonB = (ToggleButton) findViewById(R.id.button_b);
        ToggleButton buttonI = (ToggleButton) findViewById(R.id.button_i);
        Button buttonSans = (Button) findViewById(R.id.button_sans);
        Button buttonSer = (Button) findViewById(R.id.button_serif);
        Button buttonMono = (Button) findViewById(R.id.button_monospace);
        Button buttonPlus = (Button) findViewById(R.id.button_plus);
        Button buttonMinus = (Button) findViewById(R.id.button_minus);
        ImageButton bRed = (ImageButton) findViewById(R.id.red);
        ImageButton bGreen = (ImageButton) findViewById(R.id.green);
        ImageButton bBlue = (ImageButton) findViewById(R.id.blue);
        ImageButton bCyan = (ImageButton) findViewById(R.id.cyan);
        ImageButton bMagenta = (ImageButton) findViewById(R.id.magenta);
        ImageButton bYellow = (ImageButton) findViewById(R.id.yellow);
        ImageButton bBlack = (ImageButton) findViewById(R.id.black);
        buttonB.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonSans.setOnClickListener(this);
        buttonSer.setOnClickListener(this);
        buttonMono.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        bRed.setOnClickListener(this);
        bGreen.setOnClickListener(this);
        bBlue.setOnClickListener(this);
        bCyan.setOnClickListener(this);
        bMagenta.setOnClickListener(this);
        bYellow.setOnClickListener(this);
        bBlack.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_plus:
                if (mTextSize < 72) {
                    mTextSize += 2;
                    mEdit.setTextSize(mTextSize);
                    tSize.setText(String.format("%.0f", mTextSize));
                }
                break;
            case R.id.button_minus:
                if (mTextSize > 18) {
                    mTextSize -= 2;
                    mEdit.setTextSize(mTextSize);
                    tSize.setText(String.format("%.0f", mTextSize));
                }
                break;
            case R.id.button_b:
                if (mEdit.getTypeface().getStyle() == Typeface.ITALIC)
                    mEdit.setTypeface(mEdit.getTypeface(), Typeface.BOLD_ITALIC);
                else if (mEdit.getTypeface().getStyle() ==
                        Typeface.BOLD_ITALIC) mEdit.setTypeface(mEdit.getTypeface(),
                        Typeface.ITALIC);
                else if (mEdit.getTypeface().getStyle() == Typeface.BOLD)
                    mEdit.setTypeface(Typeface.create(mEdit.getTypeface(), Typeface.NORMAL));
                else mEdit.setTypeface(mEdit.getTypeface(),
                            Typeface.BOLD);
                break;
            case R.id.button_i:
                if (mEdit.getTypeface().getStyle() == Typeface.BOLD)
                    mEdit.setTypeface(mEdit.getTypeface(), Typeface.BOLD_ITALIC);
                else if (mEdit.getTypeface().getStyle() == Typeface.BOLD_ITALIC)
                    mEdit.setTypeface(mEdit.getTypeface(), Typeface.BOLD);
                else if (mEdit.getTypeface().getStyle() == Typeface.ITALIC)
                    mEdit.setTypeface(Typeface.create(mEdit.getTypeface(), Typeface.NORMAL));
                else mEdit.setTypeface(mEdit.getTypeface(), Typeface.ITALIC);
                break;
            case R.id.button_sans:
                if (mEdit.getTypeface().getStyle() == Typeface.ITALIC)
                    mEdit.setTypeface(Typeface.SANS_SERIF,
                            Typeface.ITALIC);
                else if (mEdit.getTypeface().getStyle() ==
                        Typeface.BOLD_ITALIC) mEdit.setTypeface(Typeface.SANS_SERIF,
                        Typeface.BOLD_ITALIC);
                else if (mEdit.getTypeface().getStyle() == Typeface.BOLD)
                    mEdit.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                else mEdit.setTypeface(Typeface.SANS_SERIF,
                            Typeface.NORMAL);
                break;
            case R.id.button_serif:
                if (mEdit.getTypeface().getStyle() == Typeface.ITALIC)
                    mEdit.setTypeface(Typeface.SERIF,
                            Typeface.ITALIC);
                else if (mEdit.getTypeface().getStyle() ==
                        Typeface.BOLD_ITALIC) mEdit.setTypeface(Typeface.SERIF,
                        Typeface.BOLD_ITALIC);
                else if (mEdit.getTypeface().getStyle() == Typeface.BOLD)
                    mEdit.setTypeface(Typeface.SERIF, Typeface.BOLD);
                else mEdit.setTypeface(Typeface.SERIF,
                            Typeface.NORMAL);
                break;
            case R.id.button_monospace:
                if (mEdit.getTypeface().getStyle() == Typeface.ITALIC)
                    mEdit.setTypeface(Typeface.MONOSPACE,
                            Typeface.ITALIC);
                else if (mEdit.getTypeface().getStyle() ==
                        Typeface.BOLD_ITALIC) mEdit.setTypeface(Typeface.MONOSPACE,
                        Typeface.BOLD_ITALIC);
                else if (mEdit.getTypeface().getStyle() == Typeface.BOLD)
                    mEdit.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
                else mEdit.setTypeface(Typeface.MONOSPACE,
                            Typeface.NORMAL);
                break;

            case R.id.red:
                mEdit.setTextColor(Color.parseColor("#ff0000"));
                break;
            case R.id.blue:
                mEdit.setTextColor(Color.parseColor("#0000ff"));
                break;
            case R.id.green:
                mEdit.setTextColor(Color.parseColor("#00ff00"));
                break;
            case R.id.cyan:
                mEdit.setTextColor(Color.parseColor("#00FFFF"));
                break;
            case R.id.magenta:
                mEdit.setTextColor(Color.parseColor("#FF00FF"));
                break;
            case R.id.yellow:
                mEdit.setTextColor(Color.parseColor("#FFFF00"));
                break;
            case R.id.black:
                mEdit.setTextColor(Color.parseColor("#000000"));
                break;

        }

    }
}
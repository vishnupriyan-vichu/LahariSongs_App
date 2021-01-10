package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SongPageViewer extends AppCompatActivity {
    PDFView pdfView;
    TextView textView;
    final static float STEP = 200;
    float mRatio = 1.0f;
    int mBaseDist;
    float mBaseRatio;
    float fontsize = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_page_viewer);

//        pdfView = findViewById(R.id.pdfView);
//        pdfView.setKeepScreenOn(true);
//        pdfView.fitToWidth();
//        pdfView.setMinZoom(1.25f);
//        pdfView.setMidZoom(2f);
//        pdfView.setMaxZoom(2.75f);
//
        String song = getIntent().getExtras().getString("bookName") + "/" + getIntent().getExtras().getString("bookName") + (getIntent().getExtras().getInt("songNo") + 1);
//        System.out.println();
//        pdfView.fromAsset(song).load();

//        textView = findViewById(R.id.textview);
//
//        InputStream file = null;
//        try {
//            file = getAssets().open(song);
//            int i=file.read();
//            String songText = "";
//            while(i != -1) {
//                songText += (char)i;
//                i = file.read();
//            }
//            textView.setText(songText);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        textView = findViewById(R.id.textview);
        String text = "";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(song), "UTF-8"));
            String mLine;
            boolean isCover = false;
            if(song.endsWith("1")) {
                while ((mLine = reader.readLine()) != null) {
                    if(mLine.contains("\\cover\\")) {
                        isCover = true;
                        text += mLine.replace("\\cover\\", "      ") + "\n";
                    } else {
                        text += mLine.replace("\\t", "     ") + "\n";
                    }
                }
                textView.setText(text);
                if(isCover) {
                    textView.setTextSize(28);
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setTypeface(null, Typeface.BOLD);
                }
            } else {
                while ((mLine = reader.readLine()) != null) {
                    text += mLine.replace("\\t", "     ") + "\n";
                }
                textView.setText(text);
            }
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setKeepScreenOn(true);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                doScale(event);
                return false;
            }
        });
    }

    public boolean doScale(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            int action = event.getAction();
            int pureaction = action & MotionEvent.ACTION_MASK;
            if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
                mBaseDist = getDistance(event);
                mBaseRatio = mRatio;
            } else {
                float delta = (getDistance(event) - mBaseDist) / STEP;
                float multi = (float) Math.pow(2, delta);
                mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
                textView.setTextSize(mRatio + 13);
            }
        }
        return true;
    }

    int getDistance(MotionEvent event) {
        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));
        return (int) (Math.sqrt(dx * dx + dy * dy));
    }
}
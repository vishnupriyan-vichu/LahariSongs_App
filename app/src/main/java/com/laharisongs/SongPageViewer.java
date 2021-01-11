package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class SongPageViewer extends AppCompatActivity {
    TextView textView;
    TextView header;
    final static float STEP = 200;
    float mRatio = 1.0f;
    int mBaseDist;
    float mBaseRatio;
    float fontsize = 13;
    String bookName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_page_viewer);

        bookName = getIntent().getExtras().getString("bookName");
        String song = bookName + "/" + bookName + (getIntent().getExtras().getInt("songNo") + 1);

        textView = findViewById(R.id.textview);
        header = findViewById(R.id.header);
        String text = "";

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(song), "UTF-8"));
            String mLine;
            boolean isCover = false;
            System.out.println(bookName + " " + song);
            if(bookName.equals("SofR") || (bookName.equals("IG") && !song.endsWith("8"))) {
                textView.setAllCaps(false);
                header.setAllCaps(false);
                Typeface tamilBible = ResourcesCompat.getFont(this, R.font.tamil_bible);
                Typeface tamilBibleBold = Typeface.create(tamilBible, Typeface.BOLD);
                textView.setTypeface(tamilBibleBold);
                header.setTypeface(tamilBibleBold);
            }
            if(song.endsWith("1")) {
                mLine = reader.readLine().replace("\\t", "  ");
                header.setText(mLine);
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
                    header.setVisibility(View.GONE);
                }
            } else {
                mLine = reader.readLine().replace("\\t", "  ");
                header.setText(mLine);
                while ((mLine = reader.readLine()) != null) {
                    text += mLine.replace("\\t", "     ") + "\n";
                }
                textView.setText(text);
            }
            header.setTextSize(26);
            header.setTextColor(getResources().getColor(R.color.black));
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
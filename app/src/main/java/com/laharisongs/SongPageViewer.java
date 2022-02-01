package com.laharisongs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.laharisongs.IndexNameConstant.BookType;

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
    Bundle extras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_page_viewer);

        extras = getIntent().getExtras();
        bookName = extras.getString("bookName");
        String song = bookName + "/" + bookName + (extras.getInt("songNo") + 1);
        BookType bookType = (BookType) extras.get("bookType");

        textView = findViewById(R.id.textview);
        /*ScrollingMovementMethod movementMethod = new ScrollingMovementMethod();
        textView.setMovementMethod(movementMethod);*/
        header = findViewById(R.id.header);
        drawContent(song);
    }

    private void drawContent(String song) {
        String text = "";
        BufferedReader reader = null;
        Typeface tamilBibleBold = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(song), "UTF-8"));
            String mLine;
            boolean isCover = false;
            if(bookName.equals("SofR") || (bookName.equals("IG") && !song.endsWith("8"))) {
                textView.setAllCaps(false);
                header.setAllCaps(false);
                Typeface tamilBible = ResourcesCompat.getFont(this, R.font.tamil_bible);
                tamilBibleBold = Typeface.create(tamilBible, Typeface.BOLD);
                textView.setTypeface(tamilBibleBold);
                header.setTypeface(tamilBibleBold);
            }
            if(song.endsWith("1")) {
                mLine = reader.readLine().replace("\\t", "  ");
                header.setText(mLine);
                while ((mLine = reader.readLine()) != null) {
                    // checking whether the page is cover page or not
                    if(mLine.contains("\\cover\\")) {
                        isCover = true;
                        text += mLine.replace("\\cover\\", "      ") + "\n";
                    } else if(mLine.length()>0 && isNumeric(mLine.charAt(0)+"")) {
                        text += "\n" + mLine.replace("\\t", "     ") + "\n";
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
                    if(mLine.length()>0 && isNumeric(mLine.charAt(0)+"")) {
                        text += "\n" + mLine.replace("\\t", "     ") + "\n";
                    } else {
                        text += mLine.replace("\\t", "     ") + "\n";
                    }
                }
                textView.setText(text);
            }
            header.setTextSize(20);
            header.setTypeface(tamilBibleBold, Typeface.BOLD);
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

    public void gotoNextPage(View view) {
        if((extras.getInt("songNo")+1) < extras.getInt("finishingSongNo")) {
            getIntent().putExtra("bookName", bookName);
            getIntent().putExtra("songNo", extras.getInt("songNo") + 1);
            finish();
            startActivity(getIntent());
        }
    }

    public void gotoPreviousPage(View view) {
        if((extras.getInt("songNo")-1) >= 0) {
            getIntent().putExtra("bookName", bookName);
            getIntent().putExtra("songNo", extras.getInt("songNo") - 1);
            finish();
            startActivity(getIntent());
        }
    }

    private boolean doScale(MotionEvent event) {
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

    private int getDistance(MotionEvent event) {
        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));
        return (int) (Math.sqrt(dx * dx + dy * dy));
    }

    private boolean isNumeric(String numericString) {
        try {
            Integer intValue = Integer.parseInt(numericString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
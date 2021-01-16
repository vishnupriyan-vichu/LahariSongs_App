package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class SongIndex extends AppCompatActivity {

    public String[] songs = new String[0];
    public int bookNo;
    public int lang;
    public Button book;
    public String bookName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_index);

        LinearLayout songsIndex = (LinearLayout) findViewById(R.id.songIndex);
        bookNo = getIntent().getExtras().getInt("indexOfSong");
        lang = getIntent().getExtras().getInt("indexOflang");

        switch(lang) {
            case 0:
                bookName += BookNameConstant.TA_BOOK[bookNo];
                break;
            case 1:
                bookName += BookNameConstant.TE_BOOK[bookNo];
                break;
            case 2:
                bookName += BookNameConstant.E_BOOK[bookNo];
                break;
            case 3:
                bookName += BookNameConstant.H_BOOK[bookNo];
                break;
        }

        songs = renderIndex();

        int margin = 30;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, margin, margin, 0);

        for(int i=0; i<songs.length; i++) {
            book = new Button(this);
//            book.setAllCaps(false);
//            if(bookName.equalsIgnoreCase("sofr")) {
//                Typeface tamilBible = ResourcesCompat.getFont(this, R.font.tamil_bible);
//                Typeface tamilBibleBold = Typeface.create(tamilBible, Typeface.BOLD);
//                book.setTypeface(tamilBibleBold);
//            }
            book.setText(songs[i]);
            book.setId(i);
            book.setBackgroundColor(getResources().getColor(R.color.button));
            book.setLayoutParams(params);
            book.setOnClickListener(v -> {
                addClick(v.getId());
            });
            songsIndex.addView(book);
        }
    }

    public String[] renderIndex() {
        switch(bookName) {
            case "SofR" :
                return IndexNameConstant.SofR;
            case "IG" :
                return IndexNameConstant.IG;
            case "LN" :
                return IndexNameConstant.LN;
            case "ES" :
                return IndexNameConstant.ES;
            case "EC" :
                return IndexNameConstant.EC;
            case "GHc" :
                return IndexNameConstant.GHc;
            case "HS" :
                return IndexNameConstant.HS;
            case "GN" :
                return IndexNameConstant.GN;
            case "PS" :
                return IndexNameConstant.PS;
            case "HP" :
                return IndexNameConstant.HP;
        }
        return null;
    }

    public void addClick(int n) {
        Intent intent = new Intent(this, SongPageViewer.class);
        intent.putExtra("bookName", bookName);
        intent.putExtra("songNo", n);
        intent.putExtra("finishingSongNo", songs.length);
        startActivity(intent);
    }
}
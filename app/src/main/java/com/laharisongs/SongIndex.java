package com.laharisongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.laharisongs.IndexNameConstant.BookType;

public class SongIndex extends AppCompatActivity {

    private String[] contents = null;
    private String bookName = "";
    private BookType bookType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_index);

        LinearLayout songsIndex = (LinearLayout) findViewById(R.id.songIndex);
        songsIndex.setPadding(10, 10, 10, 10);
        int bookNo = getIntent().getExtras().getInt("indexOfSong");
        int lang = getIntent().getExtras().getInt("indexOflang");

        switch(lang) {
            case 0:
                bookName += BookNameConstant.TA_BOOK[bookNo];
                bookType = BookType.SONGS;
                break;
            case 1:
                bookName += BookNameConstant.TE_BOOK[bookNo];
                bookType = BookType.SONGS;
                break;
            case 2:
                bookName += BookNameConstant.E_BOOK[bookNo];
                bookType = BookType.SONGS;
                break;
            case 3:
                bookName += BookNameConstant.H_BOOK[bookNo];
                bookType = BookType.SONGS;
                break;
            case 4:
                bookName += BookNameConstant.GITA_BOOK[bookNo];
                bookType = BookType.SCRIPT;
                break;
        }

        contents = renderIndex();

        int margin = 30;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, margin, margin, 0);
        generateIndex(params, songsIndex);
    }

    private void generateIndex(LinearLayout.LayoutParams params, LinearLayout songsIndex) {
        for(int i=0; i<contents.length; i++) {
            Button item = new Button(this);
//            book.setAllCaps(false);
//            if(bookName.equalsIgnoreCase("sofr")) {
//                Typeface tamilBible = ResourcesCompat.getFont(this, R.font.tamil_bible);
//                Typeface tamilBibleBold = Typeface.create(tamilBible, Typeface.BOLD);
//                book.setTypeface(tamilBibleBold);
//            }
            if(bookType.equals(BookType.SONGS)) {
                item.setText(i+1+"."+contents[i]);
            } else {
                item.setText(contents[i]);
            }
            item.setGravity(Gravity.CENTER_VERTICAL);
            item.setPadding(10, 10, 10, 10);
            item.setId(i);
            item.setBackgroundColor(getResources().getColor(R.color.button));
            item.setLayoutParams(params);
            item.setOnClickListener(v -> {
                addClick(v.getId());
            });
            songsIndex.addView(item);
        }
    }

    private String[] renderIndex() {
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
            case "LK" :
                return IndexNameConstant.LK;
            case "BG_E" :
                return IndexNameConstant.BG_E;
            case "BG_T" :
                return IndexNameConstant.BG_T;
            case "BG_TE" :
                return IndexNameConstant.BG_TE;
            case "OTS" :
                return IndexNameConstant.OTS;
            case "OTSC" :
                return IndexNameConstant.OTSC;
            case "OTSE" :
                return IndexNameConstant.OTSE;
            case "OTSM" :
                return IndexNameConstant.OTSM;
            case "OTST" :
                return IndexNameConstant.OTST;
        }
        return null;
    }

    public void addClick(int n) {
        Intent intent = new Intent(this, SongPageViewer.class);
        intent.putExtra("bookName", bookName);
        intent.putExtra("songNo", n);
        intent.putExtra("bookType", bookType);
        intent.putExtra("finishingSongNo", contents.length);
        startActivity(intent);
    }
}
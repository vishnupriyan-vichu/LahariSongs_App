package com.laharisongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.laharisongs.util.BookNameConstants;
import com.laharisongs.util.IndexNameConstant;

public class LanguagesActivity extends AppCompatActivity {

    Button tamilSongs;
    Button teluguSongs;
    Button hindiSongs;
    Button englishSongs;
    Button englishGita;
    Button tamilGita;
    Button teluguGita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Intent intent = getIntent();
        int type = intent.getIntExtra(BookNameConstants.TYPE_SELECTED, 1);

        tamilSongs = findViewById(R.id.tamil);
        teluguSongs = findViewById(R.id.telugu);
        englishSongs = findViewById(R.id.english);
        hindiSongs = findViewById(R.id.hindi);
        englishGita = findViewById(R.id.englishGita);
        tamilGita = findViewById(R.id.tamilGita);
        teluguGita = findViewById(R.id.teluguGita);

        ViewGroup layout = (ViewGroup) tamilSongs.getParent();
        if (type == IndexNameConstant.BookType.SONGS.getId()) {
            if (layout != null) {
                layout.removeView(englishGita);
                layout.removeView(tamilGita);
                layout.removeView(teluguGita);
            }
            tamilSongs.setOnClickListener(v -> {
                createSongBookIntent(BookNameConstants.LangKey.TAMIL);
            });
            teluguSongs.setOnClickListener(v -> {
                createSongBookIntent(BookNameConstants.LangKey.TELUGU);
            });
            englishSongs.setOnClickListener(v -> {
                createSongBookIntent(BookNameConstants.LangKey.ENGLISH);
            });
            hindiSongs.setOnClickListener(v -> {
                createSongBookIntent(BookNameConstants.LangKey.HINDI);
            });
        } else if (type == IndexNameConstant.BookType.SCRIPT.getId()) {
            if (layout != null) {
                layout.removeView(tamilSongs);
                layout.removeView(teluguSongs);
                layout.removeView(englishSongs);
                layout.removeView(hindiSongs);
            }
            englishGita.setOnClickListener(v -> {
                createGitaIntent(BookNameConstants.BookKey.BG_E);
            });
            tamilGita.setOnClickListener(v -> {
                createGitaIntent(BookNameConstants.BookKey.BG_TA);
            });
            teluguGita.setOnClickListener(v -> {
                createGitaIntent(BookNameConstants.BookKey.BG_TE);
            });
        }
    }

    private void createSongBookIntent(String language) {
        Intent intent = new Intent(this, SongBooksListActivity.class);
        intent.putExtra(BookNameConstants.LANGUAGE_SELECTED, language);
        startActivity(intent);
    }

    private void createGitaIntent(String language) {
        Intent intent = new Intent(this, IndexActivity.class);
        intent.putExtra("indexOfSong", language);
        intent.putExtra("bookType", 2);
        startActivity(intent);
    }
}
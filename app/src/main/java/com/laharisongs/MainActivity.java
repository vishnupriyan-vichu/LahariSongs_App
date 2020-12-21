package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button tamilSongs;
    Button teluguSongs;
    Button hindiSongs;
    Button englishSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tamilSongs = findViewById(R.id.tamil);
        teluguSongs = findViewById(R.id.telugu);
        englishSongs = findViewById(R.id.english);
        hindiSongs = findViewById(R.id.hindi);

        tamilSongs.setOnClickListener(v -> {
            createBookActivity(BookConstant.TAMIL_SONG_BOOK);
        });
        teluguSongs.setOnClickListener(v -> {
            createBookActivity(BookConstant.TELUGU_SONG_BOOK);
        });
        englishSongs.setOnClickListener(v -> {
            createBookActivity(BookConstant.ENGLISH_SONG_BOOK);
        });
        hindiSongs.setOnClickListener(v -> {
            createBookActivity(BookConstant.HINDI_SONG_BOOK);
        });
    }

    protected void createBookActivity(BookConstant language) {
        Intent intent = new Intent(this, SongBooksListActivity.class);
        switch(language) {
            case TAMIL_SONG_BOOK:
                intent.putExtra(BookConstant.LANGUAGE_SELECTED, "TAMIL_SONG_BOOK");
                break;
            case TELUGU_SONG_BOOK:
                intent.putExtra(BookConstant.LANGUAGE_SELECTED, "TELUGU_SONG_BOOK");
                break;
            case ENGLISH_SONG_BOOK:
                intent.putExtra(BookConstant.LANGUAGE_SELECTED, "ENGLISH_SONG_BOOK");
                break;
            case HINDI_SONG_BOOK:
                intent.putExtra(BookConstant.LANGUAGE_SELECTED, "HINDI_SONG_BOOK");
                break;
            default:
                break;
        }
        startActivity(intent);
    }

}
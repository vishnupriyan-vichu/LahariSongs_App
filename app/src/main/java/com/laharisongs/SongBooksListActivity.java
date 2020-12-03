package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SongBooksListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_books_list);

        Intent intent = getIntent();
        String language = intent.getStringExtra(BookConstant.LANGUAGE_SELECTED);

        switch (language) {
            case "TAMIL_SONG_BOOK" :
                break;
            case "TELUGU_SONG_BOOK" :
                break;
            case "ENGLISH_SONG_BOOK" :
                break;
            case "HINDI_SONG_BOOK" :
                break;
        }
    }

    protected void generateTamilBooks() {

    }

    protected void generateTeluguBooks() {

    }

    protected void generateEnglishBooks() {

    }

    protected void generateHindiBooks() {
        
    }
}
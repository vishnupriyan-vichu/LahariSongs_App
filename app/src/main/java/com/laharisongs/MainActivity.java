package com.laharisongs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    Button songsBook;
    Button scripturesBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        songsBook = findViewById(R.id.songsBook);
        scripturesBook = findViewById(R.id.scripturesBook);

        songsBook.setOnClickListener(v -> {
            createBookActivity(BookConstant.SONGS_BOOKS);
        });
        scripturesBook.setOnClickListener(v -> {
            createBookActivity(BookConstant.SCRIPTURES_BOOKS);
        });
    }

    protected void createBookActivity(BookConstant language) {
        Intent intent = new Intent(this, BooksListActivity.class);
        intent.putExtra(BookConstant.TYPE_SELECTED, language.name());
        startActivity(intent);
    }

}
package com.laharisongs;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.laharisongs.util.BookNameConstants;
import com.laharisongs.util.IndexNameConstant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        findViewById(R.id.songsBook).setOnClickListener(v -> {
            createBookActivity(IndexNameConstant.BookType.SONGS);
        });
        findViewById(R.id.scripturesBook).setOnClickListener(v -> {
            createBookActivity(IndexNameConstant.BookType.SCRIPT);
        });
    }

    protected void createBookActivity(IndexNameConstant.BookType bookType) {
        Intent intent = new Intent(this, LanguagesActivity.class);
        intent.putExtra(BookNameConstants.TYPE_SELECTED, bookType.getId());
        startActivity(intent);
    }

}
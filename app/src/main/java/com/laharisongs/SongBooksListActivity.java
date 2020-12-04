package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class SongBooksListActivity extends AppCompatActivity {

    public String[] books = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_books_list);

        Intent intent = getIntent();
        String language = intent.getStringExtra(BookConstant.LANGUAGE_SELECTED);

        generateBooks(language);
    }

    protected void generateBooks(String language) {
        LinearLayout booksIndex = (LinearLayout) findViewById(R.id.booksIndex);
        Button book;

        switch (language) {
            case "TAMIL_SONG_BOOK" :
                books = BookNameConstant.TAMIL_BOOKS;
                break;
            case "TELUGU_SONG_BOOK" :
                books = BookNameConstant.TELUGU_BOOKS;
                break;
            case "ENGLISH_SONG_BOOK" :
                books = BookNameConstant.ENGLISH_BOOKS;
                break;
            case "HINDI_SONG_BOOK" :
                books = BookNameConstant.HINDI_BOOKS;
                break;
        }

        for(int i=0; i<books.length; i++) {
            book = new Button(this);
            book.setText(books[i]);
            book.setId(i);
            booksIndex.addView(book);
        }
    }
}
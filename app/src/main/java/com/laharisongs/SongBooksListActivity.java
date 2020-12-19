package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

public class SongBooksListActivity extends AppCompatActivity {

    public String[] books = new String[0];
    public int lang;

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
                lang = 0;
                break;
            case "TELUGU_SONG_BOOK" :
                books = BookNameConstant.TELUGU_BOOKS;
                lang = 1;
                break;
            case "ENGLISH_SONG_BOOK" :
                books = BookNameConstant.ENGLISH_BOOKS;
                lang = 2;
                break;
            case "HINDI_SONG_BOOK" :
                books = BookNameConstant.HINDI_BOOKS;
                lang = 3;
                break;
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        for(int i=0; i<books.length; i++) {
            book = new Button(this);
            book.setText(books[i]);
            book.setId(i);
            book.setBackgroundColor(getResources().getColor(R.color.button));
            book.setLayoutParams(params);
            book.setOnClickListener(v -> {
                afterClick(v.getId());
            });
            booksIndex.addView(book);
        }
    }

    public void afterClick(int n) {
        Intent intent = new Intent(this, SongIndex.class);
        intent.putExtra("indexOflang", lang);
        intent.putExtra("indexOfSong", n);
        startActivity(intent);
    }
}
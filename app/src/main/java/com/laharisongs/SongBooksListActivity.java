package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SongBooksListActivity extends AppCompatActivity {

    public String[] books = new String[0];
    public int lang;
    TextView languageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_books_list);

        Intent intent = getIntent();
        String language = intent.getStringExtra(BookConstant.LANGUAGE_SELECTED);
        languageView = findViewById(R.id.language);

        generateBooks(language);
    }

    protected void generateBooks(String language) {
        LinearLayout booksIndex = (LinearLayout) findViewById(R.id.booksIndex);
        Button book;

        switch (language) {
            case "TAMIL_SONG_BOOK" :
                books = BookNameConstant.TAMIL_BOOKS;
                lang = 0;
                languageView.setText(getString(R.string.tamil_button));
                break;
            case "TELUGU_SONG_BOOK" :
                books = BookNameConstant.TELUGU_BOOKS;
                lang = 1;
                languageView.setText(getString(R.string.telugu_button));
                break;
            case "ENGLISH_SONG_BOOK" :
                books = BookNameConstant.ENGLISH_BOOKS;
                lang = 2;
                languageView.setText(getString(R.string.english_button));
                break;
            case "HINDI_SONG_BOOK" :
                books = BookNameConstant.HINDI_BOOKS;
                lang = 3;
                languageView.setText(getString(R.string.hindi_button));
                break;
        }

        languageView.setTextSize(28f);
        languageView.setPadding(10,10,10,10);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        for(int i=0; i<books.length; i++) {
            book = new Button(this);
            if(lang==0) {
                book.setAllCaps(false);
                Typeface tamilBible = ResourcesCompat.getFont(this, R.font.tamil_bible);
                Typeface tamilBibleBold = Typeface.create(tamilBible, Typeface.BOLD);
                book.setTypeface(tamilBibleBold);
            }
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

    public void openSearchActivity(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("lang", lang+"");
        startActivity(intent);
    }

    public void afterClick(int n) {
        Intent intent = new Intent(this, SongIndex.class);
        intent.putExtra("indexOflang", lang);
        intent.putExtra("indexOfSong", n);
        startActivity(intent);
    }
}
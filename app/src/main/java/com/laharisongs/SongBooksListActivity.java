package com.laharisongs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.laharisongs.util.BookNameConstants;
import com.laharisongs.util.BookNameConstants.BooksConstant;

public class SongBooksListActivity extends AppCompatActivity {

    private BooksConstant bookConstant = null;
    public int lang;
    TextView languageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_books_list);

        Intent intent = getIntent();
        String language = intent.getStringExtra(BookNameConstants.LANGUAGE_SELECTED);
        languageView = findViewById(R.id.language);

        generateBooks(language);
    }

    protected void generateBooks(String language) {
        LinearLayout booksIndex = (LinearLayout) findViewById(R.id.booksIndex);
        Button book;

        switch (language) {
            case BookNameConstants.LangKey.TAMIL:
                bookConstant = BooksConstant.TAMIL_BOOKS;
                languageView.setText(getString(R.string.tamil_button));
                break;
            case BookNameConstants.LangKey.TELUGU:
                bookConstant = BooksConstant.TELUGU_BOOKS;
                languageView.setText(getString(R.string.telugu_button));
                break;
            case BookNameConstants.LangKey.ENGLISH:
                bookConstant = BooksConstant.ENGLISH_BOOKS;
                languageView.setText(getString(R.string.english_button));
                break;
            case BookNameConstants.LangKey.HINDI:
                bookConstant = BooksConstant.HINDI_BOOKS;
                languageView.setText(getString(R.string.hindi_button));
                break;
        }
        lang = bookConstant.getLang();

        languageView.setTextSize(28f);
        languageView.setPadding(10, 10, 10, 10);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        for (String bookKey : bookConstant.getBookKeys()) {
            book = new Button(this);
            if (lang == BooksConstant.TAMIL_BOOKS.getLang()) {
                book.setAllCaps(false);
                Typeface tamilBible = ResourcesCompat.getFont(this, R.font.tamil_bible);
                Typeface tamilBibleBold = Typeface.create(tamilBible, Typeface.BOLD);
                book.setTypeface(tamilBibleBold);
            }
            book.setText(bookConstant.getBookName(bookKey));
            book.setBackgroundColor(getResources().getColor(R.color.button));
            book.setLayoutParams(params);
            book.setOnClickListener(v -> afterClick(bookKey));
            booksIndex.addView(book);
        }
    }

    public void openSearchActivity(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("lang", lang);
        startActivity(intent);
    }

    public void afterClick(String bookKey) {
        Intent intent = new Intent(this, IndexActivity.class);
        intent.putExtra("indexOfSong", bookKey);
        intent.putExtra("bookType", 1);
        startActivity(intent);
    }
}
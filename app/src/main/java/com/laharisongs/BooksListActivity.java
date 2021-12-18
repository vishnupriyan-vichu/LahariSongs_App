package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class BooksListActivity extends AppCompatActivity {

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
        String type = intent.getStringExtra(BookConstant.TYPE_SELECTED);
        System.out.println("\n"+type+"\n");

        tamilSongs = findViewById(R.id.tamil);
        teluguSongs = findViewById(R.id.telugu);
        englishSongs = findViewById(R.id.english);
        hindiSongs = findViewById(R.id.hindi);
        englishGita = findViewById(R.id.englishGita);
        tamilGita = findViewById(R.id.tamilGita);
        teluguGita = findViewById(R.id.teluguGita);
        ViewGroup layout = (ViewGroup) tamilSongs.getParent();
        if(type.equals(BookConstant.SONGS_BOOKS.name())) {
            if(layout != null) {
                layout.removeView(englishGita);
                layout.removeView(tamilGita);
                layout.removeView(teluguGita);
            }
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
        } else if(type.equals(BookConstant.SCRIPTURES_BOOKS.name())) {
            if(layout != null) {
                layout.removeView(tamilSongs);
                layout.removeView(teluguSongs);
                layout.removeView(englishSongs);
                layout.removeView(hindiSongs);
            }
            englishGita.setOnClickListener(v -> {
                createBookActivity(BookConstant.ENGLISH_BHAGAVAD_GITA);
            });
            tamilGita.setOnClickListener(v -> {
                createBookActivity(BookConstant.TAMIL_BHAGAVAD_GITA);
            });
            teluguGita.setOnClickListener(v -> {
                createBookActivity(BookConstant.TELUGU_BHAGAVAD_GITA);
            });
        }
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
            case ENGLISH_BHAGAVAD_GITA:
                intent = new Intent(this, SongIndex.class);
                intent.putExtra("indexOflang", 4);
                intent.putExtra("indexOfSong", 0);
                break;
            case TAMIL_BHAGAVAD_GITA:
                intent = new Intent(this, SongIndex.class);
                intent.putExtra("indexOflang", 4);
                intent.putExtra("indexOfSong", 1);
                break;
            case TELUGU_BHAGAVAD_GITA:
                intent = new Intent(this, SongIndex.class);
                intent.putExtra("indexOflang", 4);
                intent.putExtra("indexOfSong", 2);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
package com.laharisongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.laharisongs.util.IndexNameConstant;
import com.laharisongs.util.IndexNameConstant.BookType;

public class SongIndexActivity extends AppCompatActivity {

    private String[] contents = null;
    private String bookKey = "";
    private int lang;
    private BookType bookType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_index);

        LinearLayout songsIndex = (LinearLayout) findViewById(R.id.songIndex);
        songsIndex.setPadding(10, 10, 10, 10);
        bookKey = getIntent().getExtras().getString("indexOfSong");
        bookType = BookType.getType(getIntent().getExtras().getInt("bookType"));
        contents = IndexNameConstant.getIndex(bookKey);

        int margin = 30;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, margin, margin, 0);
        generateIndex(params, songsIndex);
    }

    private void generateIndex(LinearLayout.LayoutParams params, LinearLayout songsIndex) {
        for (int i = 0; i < contents.length; i++) {
            Button item = new Button(this);
            if (bookType == BookType.SONGS) {
                item.setText(i + 1 + ". " + contents[i]);
            } else {
                item.setText(contents[i]);
            }
            item.setGravity(Gravity.CENTER_VERTICAL);
            item.setPadding(10, 10, 10, 10);
            item.setId(i);
            item.setBackgroundColor(getResources().getColor(R.color.button));
            item.setLayoutParams(params);
            item.setOnClickListener(v -> {
                addClick(v.getId());
            });
            songsIndex.addView(item);
        }
    }

    public void addClick(int n) {
        Intent intent = new Intent(this, SongPageViewerActivity.class);
        System.out.println(bookKey + n);
        intent.putExtra("bookName", bookKey);
        intent.putExtra("songNo", n);
        intent.putExtra("bookType", bookType);
        intent.putExtra("finishingSongNo", contents.length);
        startActivity(intent);
    }
}
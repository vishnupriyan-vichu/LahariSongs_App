package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class SongPageViewer extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_page_viewer);

        pdfView = findViewById(R.id.pdfView);
        pdfView.setKeepScreenOn(true);
        pdfView.fitToWidth();
        pdfView.setMinZoom(1.75f);
        pdfView.setMidZoom(2f);
        pdfView.setMaxZoom(2.5f);

        String song = getIntent().getExtras().getString("bookName")+"/"+getIntent().getExtras().getString("bookName")+(getIntent().getExtras().getInt("songNo")+1)+".pdf";
        System.out.println();
        pdfView.fromAsset(song).load();
    }
}
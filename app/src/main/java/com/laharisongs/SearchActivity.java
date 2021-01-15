package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchActivity extends AppCompatActivity {

    public int lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        lang = Integer.parseInt(intent.getStringExtra("lang"));

        ArrayList<String> indexedToSearch = new ArrayList<String>();

        switch(lang) {
            case 0:
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.SofR));
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.IG));
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.LN));
                break;
            case 1:
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.GN));
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.PS));
                break;
            case 2:
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.ES));
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.EC));
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.GHc));
                break;
            case 3:
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.HS));
                indexedToSearch.addAll(Arrays.asList(IndexNameConstant.HP));
                break;
        }
    }
}
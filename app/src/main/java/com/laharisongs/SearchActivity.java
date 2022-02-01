package com.laharisongs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public int lang;
    SearchView searchView;
    ListView listView;
    ArrayList<String> result;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUpRequired();
    }

    public void setUpRequired() {
        Intent intent = getIntent();
        lang = Integer.parseInt(intent.getStringExtra("lang"));

        HashMap<String, List<String>> indexedToSearch = new HashMap<String, List<String>>();

        searchView = findViewById(R.id.searchBox);
        listView = findViewById(R.id.listView);
        if(adapter != null) {
            adapter.clear();
            listView.setAdapter(adapter);
        }

        SearchActivity instance = this;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                result = new ArrayList<String>();
                Iterator<String> bookNames = indexedToSearch.keySet().iterator();
                ArrayList<String> indexList = new ArrayList<String>();
                while(bookNames.hasNext()) {
                    String bookName = bookNames.next();
                    List<String> index = indexedToSearch.get(bookName);
                    int noOfSongs = index.size();
                    for(int i=0; i<noOfSongs; i++) {
                        if(index.get(i).contains(query) || index.get(i).toLowerCase().contains(query.toLowerCase())) {
                            indexList.add(index.get(i));
                            result.add(bookName+"/"+i);
                            System.out.println(index.get(i)+bookName+i);
                        }
                    }
                    adapter = new ArrayAdapter<String>(instance, android.R.layout.simple_list_item_1, indexList);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(instance, SongPageViewer.class);
                            intent.putExtra("bookName", result.get(position).split("/")[0]);
                            intent.putExtra("songNo", Integer.parseInt(result.get(position).split("/")[1]));
                            intent.putExtra("finishingSongNo", noOfSongs);
                            //based on item add info to intent
                            startActivity(intent);
                        }
                    });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        switch(lang) {
            case 0:
                indexedToSearch.put("SofR", Arrays.asList(IndexNameConstant.SofR));
                indexedToSearch.put("IG", Arrays.asList(IndexNameConstant.IG));
                indexedToSearch.put("LN",Arrays.asList(IndexNameConstant.LN));
                indexedToSearch.put("OTSC",Arrays.asList(IndexNameConstant.OTSC));
                indexedToSearch.put("OTSM",Arrays.asList(IndexNameConstant.OTSM));
                indexedToSearch.put("OTST",Arrays.asList(IndexNameConstant.OTST));
                indexedToSearch.put("OTS",Arrays.asList(IndexNameConstant.OTS));
                break;
            case 1:
                indexedToSearch.put("GN", Arrays.asList(IndexNameConstant.GN));
                indexedToSearch.put("PS", Arrays.asList(IndexNameConstant.PS));
                indexedToSearch.put("LK", Arrays.asList(IndexNameConstant.LK));
                break;
            case 2:
                indexedToSearch.put("ES", Arrays.asList(IndexNameConstant.ES));
                indexedToSearch.put("EC", Arrays.asList(IndexNameConstant.EC));
                indexedToSearch.put("GHc", Arrays.asList(IndexNameConstant.GHc));
                indexedToSearch.put("OTSE", Arrays.asList(IndexNameConstant.OTSE));
                break;
            case 3:
                indexedToSearch.put("HS", Arrays.asList(IndexNameConstant.HS));
                indexedToSearch.put("HP", Arrays.asList(IndexNameConstant.HP));
                break;
        }
    }

    protected void onResume() {
        super.onResume();
//        setUpRequired();
    }
}
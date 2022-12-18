package com.laharisongs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.laharisongs.util.BookNameConstants;
import com.laharisongs.util.IndexNameConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    public int lang;
    SearchView searchView;
    ListView listView;
    ArrayList<String> indexList = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUpRequired();
    }

    public void setUpRequired() {
        Intent intent = getIntent();
        System.out.println("lang " + lang);
        lang = intent.getIntExtra("lang", 0);

        HashMap<String, String[]> indexedToSearch = new HashMap<>();

        searchView = findViewById(R.id.searchBox);
        listView = findViewById(R.id.listView);
        if (adapter != null) {
            adapter.clear();
            listView.setAdapter(adapter);
        }

        SearchActivity instance = this;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (result.size() > 0) {
                    result.clear();
                }
                if (indexList.size() > 0) {
                    indexList.clear();
                }
                Set<Map.Entry<String, String[]>> entries = indexedToSearch.entrySet();
                for (Map.Entry<String, String[]> entry : entries) {
                    String bookName = entry.getKey();
                    String[] index = entry.getValue();
                    if (index != null) {
                        int noOfSongs = index.length;
                        for (int i = 0; i < noOfSongs; i++) {
                            if (index[i].contains(query) || (lang == BookNameConstants.BooksConstant.ENGLISH_BOOKS.getLang() && index[i].toLowerCase().contains(query.toLowerCase()))) {
                                indexList.add(index[i]);
                                result.add(bookName + "/" + i);
                            }
                        }
                        adapter = new ArrayAdapter<>(instance, android.R.layout.simple_list_item_1, indexList);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener((parent, view, position, id) -> {
                            Intent intent1 = new Intent(instance, ViewerActivity.class);
                            intent1.putExtra("bookName", result.get(position).split("/")[0]);
                            intent1.putExtra("songNo", Integer.parseInt(result.get(position).split("/")[1]));
                            intent1.putExtra("finishingSongNo", noOfSongs);
                            //based on item add info to intent
                            startActivity(intent1);
                        });
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        loadBookNames(indexedToSearch);
//        switch(lang) {
//            case 0:
//                indexedToSearch.put("SofR", Arrays.asList(IndexNameConstant.SofR));
//                indexedToSearch.put("SofR2-", Arrays.asList(IndexNameConstant.SofRII));
//                indexedToSearch.put("IG", Arrays.asList(IndexNameConstant.IG));
//                indexedToSearch.put("LN",Arrays.asList(IndexNameConstant.LN));
//                indexedToSearch.put("OTSC",Arrays.asList(IndexNameConstant.OTSC));
//                indexedToSearch.put("OTSM",Arrays.asList(IndexNameConstant.OTSM));
//                indexedToSearch.put("OTST",Arrays.asList(IndexNameConstant.OTST));
//                indexedToSearch.put("OTS",Arrays.asList(IndexNameConstant.OTS));
//                indexedToSearch.put("CMTA",Arrays.asList(IndexNameConstant.CMTA));
//                break;
//            case 1:
//                indexedToSearch.put("GN", Arrays.asList(IndexNameConstant.GN));
//                indexedToSearch.put("PS", Arrays.asList(IndexNameConstant.PS));
//                indexedToSearch.put("LK", Arrays.asList(IndexNameConstant.LK));
//                indexedToSearch.put("CMTE", Arrays.asList(IndexNameConstant.CMTE));
//                break;
//            case 2:
//                indexedToSearch.put("ES", Arrays.asList(IndexNameConstant.ES));
//                indexedToSearch.put("EC", Arrays.asList(IndexNameConstant.EC));
//                indexedToSearch.put("GHc", Arrays.asList(IndexNameConstant.GHc));
//                indexedToSearch.put("OTSE", Arrays.asList(IndexNameConstant.OTSE));
//                break;
//            case 3:
//                indexedToSearch.put("HS", Arrays.asList(IndexNameConstant.HS));
//                indexedToSearch.put("HP", Arrays.asList(IndexNameConstant.HP));
//                break;
//        }
    }

    private void loadBookNames(Map<String, String[]> indexToSearch) {
        BookNameConstants.BooksConstant books = BookNameConstants.BooksConstant.getBooks(lang);
        List<String> bookKeys = books.getBookKeys();
        if (bookKeys != null) {
            for (String bookKey : bookKeys) {
                indexToSearch.put(bookKey, IndexNameConstant.getIndex(bookKey));
            }
        }
    }

    protected void onResume() {
        super.onResume();
    }
}
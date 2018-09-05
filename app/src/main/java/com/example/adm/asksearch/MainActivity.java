package com.example.adm.asksearch;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lista);

        arrayP = new ArrayList<>();
        arrayP.addAll(Arrays.asList(getResources().getStringArray(R.array.array_Q)));

        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,arrayP);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.lista);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                if(newText != null && !newText.isEmpty()){
                    List<String> found = new ArrayList<String>();
                    for(String items : arrayP){
                        if(items.toLowerCase().contains(newText.toLowerCase())){
                            found.add(items);
                        }
                    }
                    adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,found);
                    listView.setAdapter(adapter);

                }else {
                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayP);
                    listView.setAdapter(adapter);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}

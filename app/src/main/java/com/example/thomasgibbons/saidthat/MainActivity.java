package com.example.thomasgibbons.saidthat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;


import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] foods = {"bacon", "eggs", "cheese", "apples"};
        final ListView quotesTableListView = (ListView) findViewById(R.id.quotesTable);
        final ArrayList<String> quoteStrings = new ArrayList<String>();
        final ListAdapter customListAdapter = new CustomAdapter(this, foods);
        quotesTableListView.setAdapter(customListAdapter);
        final ListAdapter testViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quoteStrings);
        quotesTableListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String quote = String.valueOf(parent.getItemAtPosition(position));
                        Log.d("test", "here is the quote " + quote);
                    }
                }
        );
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "U4nyV96OX6B8JtCGjueRagLHFubuF2csIebq1C0e", "flDRwodKghZtxLJlER3uCBEbsoj7HOeXitHYyDOA");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Quote");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> quotes, ParseException e) {
                if (e == null) {

                    for (ParseObject quote : quotes) {
                        String quoteText = quote.getString("quoteText");
                        Log.d("quote", "Retrieved " + quoteText);
                        quoteStrings.add(quoteText);
                    }
//                    quotesTableListView.setAdapter(testViewAdapter);

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.fatrain.android.recyclerview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();
    private Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataSetOnRecyclerView();

        // Click FAB for Add item.
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                //Add a new word to the word list.
                mWordList.addLast("+ word " + wordListSize);
                //Notify the adapter that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                //Scroll to the bottom
                mRecyclerView.smoothScrollToPosition(wordListSize);

            }
        });
    }

    //For Data set on recycler view.
    public void dataSetOnRecyclerView(){
        //Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("word " + i);
        }
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    // For option_menu clickable.
    // & Rest Data for Recycler View.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Handle item selection
        switch (item.getItemId()){
            case R.id.action_reset:
                //Clear Previous Data from recycler view.
                mWordList.clear();
                mRecyclerView.getAdapter().notifyDataSetChanged();
                //Data Reset.
                dataSetOnRecyclerView();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }


}

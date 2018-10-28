package com.app.dr1009.emptyrecyclerviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.dr1009.emptyrecyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final RecyclerAdapter mAdapter = new RecyclerAdapter();
    private final List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init EmptyRecyclerView
        EmptyRecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(mAdapter);

        // init Buttons
        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add("Item No." + (mData.size() + 1));
                mAdapter.submitList(new ArrayList<>(mData));
            }
        });

        Button clearButton = findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.clear();
                mAdapter.submitList(null);
            }
        });
    }
}

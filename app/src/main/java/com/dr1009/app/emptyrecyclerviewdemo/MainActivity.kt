package com.dr1009.app.emptyrecyclerviewdemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dr1009.app.emptyrecyclerview.EmptyRecyclerView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init EmptyRecyclerView
        val recyclerView = findViewById<EmptyRecyclerView>(R.id.recycler)
        val adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

        val data: MutableList<String> = mutableListOf()

        // init Buttons
        val addButton = findViewById<Button>(R.id.button_add)
        addButton.setOnClickListener {
            data.add("Item No." + (data.size + 1))
            adapter.submitList(data.toList())
        }
        val clearButton = findViewById<Button>(R.id.button_clear)
        clearButton.setOnClickListener {
            data.clear()
            adapter.submitList(data.toList())
        }
    }
}
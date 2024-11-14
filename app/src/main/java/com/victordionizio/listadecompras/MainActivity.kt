package com.victordionizio.listadecompras

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel : ItemsViewModel by viewModels()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        button.setOnClickListener{

            if(editText.text.isEmpty()){
                editText.error = "Preencha um valor"
                return@setOnClickListener
            }

            viewModel.addItem(editText.text.toString())
            editText.text.clear()
        }

        viewModel.itemsLiveData.observe(this){
            items -> itemsAdapter.updatesItems(items)
        }

    }
}
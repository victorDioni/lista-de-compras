package com.victordionizio.listadecompras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victordionizio.listadecompras.data.ItemsDatabase

class ItemsViewModel(private val database: ItemsDatabase) : ViewModel() {

    private var items = mutableListOf<ItemModel>()
    val itemsLiveData = MutableLiveData<List<ItemModel>>()

    fun addItem(name : String){
        val item = ItemModel(name = name, onRemove = :: removeItem)
        items.add(item)
        itemsLiveData.value = items
    }

    private fun removeItem(item: ItemModel){
        items.remove(item)
        itemsLiveData.value = items
    }

}
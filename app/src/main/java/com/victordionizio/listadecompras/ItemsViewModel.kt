package com.victordionizio.listadecompras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victordionizio.listadecompras.data.ItemEntity
import com.victordionizio.listadecompras.data.ItemsDatabase
import com.victordionizio.listadecompras.data.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(private val database: ItemsDatabase) : ViewModel() {

    val itemsLiveData = MutableLiveData<List<ItemModel>>()

    // Quando o aplicativo for aberto, acessará o BD e notificá a UI com todos os registros encontrados
    init {
        viewModelScope.launch (Dispatchers.IO){
            fetchAll()
        }
    }

    fun addItem(name : String){
        viewModelScope.launch(Dispatchers.IO){
            val entity = ItemEntity(id = 0, name = name)
            database.itemsDao().insert(entity)
            fetchAll()
        }

    }

    private fun removeItem(item : ItemModel){
        viewModelScope.launch(Dispatchers.IO){
            val entity = item.toEntity()
            database.itemsDao().delete(entity)
            fetchAll()
        }
    }

    private suspend fun fetchAll(){
        val result = database.itemsDao().getAll().map {
            it.toModel(onRemove = :: removeItem)
        }

        itemsLiveData.postValue(result)
    }

}
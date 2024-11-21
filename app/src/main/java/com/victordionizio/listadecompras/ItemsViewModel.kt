package com.victordionizio.listadecompras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    }

    private suspend fun fetchAll(){
        val result = database.itemsDao().getAll().map {
            it.toModel { onRemove = {} }
        }

        itemsLiveData.postValue(result)
    }

}
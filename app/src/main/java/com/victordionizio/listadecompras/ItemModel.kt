package com.victordionizio.listadecompras

import com.victordionizio.listadecompras.data.ItemEntity

data class ItemModel(
    val id : Long,
    val name: String,
    val onRemove : (ItemModel) -> Unit
)
// o onRemover é uma função que recebe um ItemMocel e retorna Unit

fun ItemModel.toEntity() : ItemEntity {
    return ItemEntity(
        id = this.id,
        name = this.name
    )
}


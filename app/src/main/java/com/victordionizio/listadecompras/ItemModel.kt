package com.victordionizio.listadecompras

data class ItemModel(val name: String, val onRemove : (ItemModel) -> Unit)
// o onRemover é uma função que recebe um ItemMocel e retorna Unit

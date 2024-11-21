package com.victordionizio.listadecompras.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.victordionizio.listadecompras.ItemModel

@Entity
class ItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name : String
)

/*  Função de extensão. Basicamente vai transformar um objeto ItemEntity para o objeto ItemModel
    A ideia é que, a partir de uma instancia de entidade, conseguiremos crirar uma instancia do model
    Entaão, a implementação é simplesmente a criação do model com os valores da entidade
*/

fun ItemEntity.toModel(onRemove : (ItemModel) -> Unit): ItemModel {
    return ItemModel(
        id = this.id,
        name = this.name,
        onRemove = onRemove
    )
}



package br.edu.up.rgm34508741.dados

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itens")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val preco: Double,
    val quantidade: Int
)
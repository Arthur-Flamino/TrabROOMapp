package br.edu.up.rgm34508741.dados

import kotlinx.coroutines.flow.Flow


interface ItemRepositorio {

    fun buscarTodosItensRe(): Flow<List<Item>>

    fun buscarPorIDRe(id: Int): Flow<Item?>

    suspend fun novoItemRe(item: Item)

    suspend fun atualizarItemRe(item: Item)

    suspend fun deletarItemRe(item: Item)
}
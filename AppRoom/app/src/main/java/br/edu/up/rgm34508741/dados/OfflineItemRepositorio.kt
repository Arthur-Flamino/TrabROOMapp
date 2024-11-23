package br.edu.up.rgm34508741.dados

import kotlinx.coroutines.flow.Flow

class OfflineItemRepositorio(private val itemDao: ItemDao): ItemRepositorio {

    override fun buscarTodosItensRe(): Flow<List<Item>> = itemDao.buscarTodosItens()

    override fun buscarPorIDRe(id: Int): Flow<Item?> =itemDao.buscarPorID(id)

    override suspend fun novoItemRe(item: Item) = itemDao.novoItem(item)

    override suspend fun atualizarItemRe(item: Item) = itemDao.atualizarItem(item)

    override suspend fun deletarItemRe(item: Item) = itemDao.deletarItem(item)
}
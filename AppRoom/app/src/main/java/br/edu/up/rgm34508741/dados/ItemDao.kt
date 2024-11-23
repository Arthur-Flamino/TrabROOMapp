package br.edu.up.rgm34508741.dados

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface ItemDao {

    @Query("SELECT * FROM itens ORDER BY nome ASC")
    fun buscarTodosItens(): Flow<List<Item>>

    @Query("SELECT * FROM itens WHERE id= :idx")
    fun buscarPorID(idx: Int): Flow<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun novoItem(item: Item)

    @Update
    suspend fun atualizarItem(item: Item)

    @Delete
    suspend fun deletarItem(item: Item)

}
package br.edu.up.rgm34508741.dados

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventarioDb : RoomDatabase() {

    abstract fun itemDa() : ItemDao

    companion object{
        @Volatile
        private var Instance: InventarioDb? = null

        fun getDataBase(context: Context): InventarioDb{

            return Instance ?: synchronized(this) {

                Room.databaseBuilder(context, InventarioDb::class.java, "item_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }

            }
        }
    }
}
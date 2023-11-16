package com.zekkiel.newappdesa.data


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zekkiel.newappdesa.model.Pedido
import com.zekkiel.newappdesa.TpDesaMobileApp
import com.zekkiel.newappdesa.constants.Constants
import com.zekkiel.newappdesa.data.PedidoDao as PedidoDao1


@Database(entities = [Pedido::class], version = 1, exportSchema = false)
abstract class PedidoDB : RoomDatabase() {

    abstract fun pedidoDao(): PedidoDao1

    companion object {
        @Volatile
        private var INSTANCE: PedidoDB? = null

        //singleton
        fun getDataBase(): PedidoDB {
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    TpDesaMobileApp.instance.applicationContext,
                    PedidoDB::class.java,
                    Constants.DB_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }

        }

    }
}
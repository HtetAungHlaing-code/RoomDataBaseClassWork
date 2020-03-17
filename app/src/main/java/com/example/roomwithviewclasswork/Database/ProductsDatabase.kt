package com.example.roomwithviewclasswork.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomwithviewclasswork.Dao.ProductsDao
import com.example.roomwithviewclasswork.Entity.Products


@Database(entities = arrayOf(Products::class),version = 1)
abstract class ProductsDatabase :RoomDatabase(){
    abstract fun productsDao():ProductsDao

    companion object{
        private var INSTANCE: ProductsDatabase?=null

        fun getDatabase(
            context: Context
        ):ProductsDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context,
                    ProductsDatabase::class.java,
                    "products_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}
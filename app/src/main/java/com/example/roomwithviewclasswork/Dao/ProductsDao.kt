package com.example.roomwithviewclasswork.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwithviewclasswork.Entity.Products

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product:Products)
    @Query("Select * From Product ORDER BY Name ASC")
    fun getAlphabetizedName(): LiveData<List<Products>>

    @Query("Select * From Product ORDER By id ASC")
    fun getAllProducts(): LiveData<List<Products>>

    @Query("Select * From Product Where Price >= :min AND Price <= :max ORDER By id ASC")
    fun getProductsByRange(min:Double , max:Double): LiveData<List<Products>>

    @Query("Select * From Product Where Name = :name ORDER BY id ASC")
    fun getProductsByName(name:String): LiveData<List<Products>>

    @Query("Delete From Product Where id = :id")
    suspend fun deleteById(id:Int)

}
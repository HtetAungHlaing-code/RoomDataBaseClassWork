package com.example.roomwithviewclasswork.Repository

import androidx.lifecycle.LiveData
import com.example.roomwithviewclasswork.Dao.ProductsDao
import com.example.roomwithviewclasswork.Entity.Products

class ProductsRepository (private val productsDao: ProductsDao){
    val getallProducts:LiveData<List<Products>> = productsDao.getAllProducts()

    suspend fun insert(products: Products){
        productsDao.insert(products)
    }
     suspend fun getbyName(name:String): LiveData<List<Products>> {
        return productsDao.getProductsByName(name)
    }
    suspend fun getbyRange(min:Double, max:Double): LiveData<List<Products>> {
       return productsDao.getProductsByRange(min,max)
    }
    suspend fun deletebyId(id:Int){
        productsDao.deleteById(id)
    }
}
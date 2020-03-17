package com.example.roomwithviewclasswork.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwithviewclasswork.Database.ProductsDatabase
import com.example.roomwithviewclasswork.Entity.Products
import com.example.roomwithviewclasswork.Repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel (application: Application):AndroidViewModel(application){
    private val repository:ProductsRepository

    val getallProducts:LiveData<List<Products>>
    lateinit var getbyNameList: LiveData<List<Products>>
    lateinit var getbyPriceList: LiveData<List<Products>>

    init {
        val productsDao=ProductsDatabase.getDatabase(application).productsDao()
        repository= ProductsRepository(productsDao)
        getallProducts=repository.getallProducts
    }
    fun insert(products: Products)=viewModelScope.launch {
        repository.insert(products)
    }
    fun getbyName(name:String)=viewModelScope.launch {
       getbyNameList = repository.getbyName(name)
    }
    fun getbyRange(min:Double,max:Double)=viewModelScope.launch {
        getbyPriceList=repository.getbyRange(min,max)
    }
    fun deletebyId(id:Int)=viewModelScope.launch {
        repository.deletebyId(id)
    }
}
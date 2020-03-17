package com.example.roomwithviewclasswork

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwithviewclasswork.Adapter.ProductsAdapter
import com.example.roomwithviewclasswork.Entity.Products
import com.example.roomwithviewclasswork.ViewModel.ProductsViewModel
import kotlinx.android.synthetic.main.activity_delete_by_id.*
import kotlinx.android.synthetic.main.activity_get_by_name.*
import kotlinx.android.synthetic.main.activity_get_by_price_range.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var productsViewModel:ProductsViewModel
    private val AddProductsActivityCode=1
    private val DeleteProductsByIDActivityCode =2
    private val GetProductByNameActivityCode=3
    private val GetProductByPriceRangeActivityCode=4
    private val productAdapter=ProductsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerProducts.adapter=productAdapter
        recyclerProducts.layoutManager=LinearLayoutManager(this)
        productsViewModel=ViewModelProvider(this).get(ProductsViewModel::class.java)

        btnAdd.setOnClickListener {
            startActivityForResult(Intent(this,AddProductsActivity::class.java),AddProductsActivityCode)
        }

        btnGetAll.setOnClickListener {
            productsViewModel.getallProducts.observe(
                this, Observer { productList ->productList.let { productAdapter.setProducts(it) } }
            )
        }

        btnDeleteByID.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,DeleteById::class.java),DeleteProductsByIDActivityCode)
        }
        btnGetByName.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,GetByName::class.java),GetProductByNameActivityCode)
        }
        btnGetByPrice.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity,GetByPriceRange::class.java),GetProductByPriceRangeActivityCode)
        }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==AddProductsActivityCode && resultCode== Activity.RESULT_OK){
            var productsArray= data?.getStringArrayExtra(AddProductsActivity.EXTRA_REPLY)
            var product = Products()
            product.id=productsArray?.get(0)?.toInt()
            product.name=productsArray?.get(1)
            product.price=productsArray?.get(2)?.toDouble()
            product.quantity=productsArray?.get(3)?.toInt()
            productsViewModel.insert(product)

            productsViewModel.getallProducts.observe(
                this, Observer { productList -> productList.let { productAdapter.setProducts(it) } }
            )
        }
        if(requestCode==DeleteProductsByIDActivityCode && resultCode== Activity.RESULT_OK){
            var deleteId= data?.getStringExtra(DeleteById.EXTRA_REPLY)
            var idInt = deleteId.toString().toInt()
           productsViewModel.deletebyId(idInt)
            productsViewModel.getallProducts.observe(
                this, Observer { productList -> productList.let { productAdapter.setProducts(it) } }
            )
        }
        if(requestCode==GetProductByNameActivityCode && resultCode== Activity.RESULT_OK){
            var getName= data?.getStringExtra(GetByName.EXTRA_REPLY)
            var name = getName.toString()
            productsViewModel.getbyName(name)
            productsViewModel.getbyNameList.observe(
                this, Observer { productList -> productList.let { productAdapter.setProducts(it) } }
            )
        }
        if(requestCode==GetProductByPriceRangeActivityCode && resultCode== Activity.RESULT_OK){
            var priceArray= data?.getStringArrayExtra(GetByPriceRange.EXTRA_REPLY)
            var min = priceArray!![0].toString().toDouble()
            var max = priceArray!![1].toString().toDouble()
            productsViewModel.getbyRange(min,max)
            productsViewModel.getbyPriceList.observe(
                this, Observer { productList -> productList.let { productAdapter.setProducts(it) } }
            )
        }
    }
}


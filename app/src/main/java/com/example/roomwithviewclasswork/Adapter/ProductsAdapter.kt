package com.example.roomwithviewclasswork.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwithviewclasswork.Entity.Products
import com.example.roomwithviewclasswork.R
import kotlinx.android.synthetic.main.item_products.view.*

class ProductsAdapter :RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){
    private var products= emptyList<Products>()

    inner class ProductsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind (products: Products){
            itemView.txtID.text=products.id.toString()
            itemView.txtName.text=products.name.toString()
            itemView.txtPrice.text=products.price.toString()
            itemView.txtQuantity.text=products.quantity.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.item_products,parent,false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }
    fun setProducts(products:List<Products>){
        this.products=products
        notifyDataSetChanged()
    }
}
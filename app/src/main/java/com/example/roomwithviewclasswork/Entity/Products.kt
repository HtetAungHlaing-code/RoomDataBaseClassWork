package com.example.roomwithviewclasswork.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "Product")
class Products {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
    @ColumnInfo(name= "Name")
    var name:String? =null
    @ColumnInfo(name= "Price")
    var price:Double? =null
    @ColumnInfo(name= "Quantity")
    var quantity:Int?=0
}


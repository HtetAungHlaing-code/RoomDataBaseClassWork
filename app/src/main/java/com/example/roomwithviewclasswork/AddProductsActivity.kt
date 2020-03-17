package com.example.roomwithviewclasswork

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_add_products.*

class AddProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_products)

        btnSave.setOnClickListener {
            var replyIntent=Intent()
            if(TextUtils.isEmpty(txt_Id.text) && TextUtils.isEmpty(txt_Name.text) && TextUtils.isEmpty(txt_Price.text) && TextUtils.isEmpty(txt_Quantity.text)){
                txt_Id.setError("Please Fill Them")
                txt_Name.setError("Please Fill Them")
                txt_Price.setError("Please Fill Them")
                txt_Quantity.setError("Please Fill Them")
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }
            else{
                val products:Array<String> = arrayOf(
                    txt_Id.text.toString(),
                    txt_Name.text.toString(),
                    txt_Price.text.toString(),
                    txt_Quantity.text.toString()
                )

                replyIntent.putExtra(AddProductsActivity.EXTRA_REPLY,products)
                setResult(Activity.RESULT_OK,replyIntent)
                finish()
            }
        }
    }
    companion object{
        const val EXTRA_REPLY="REPLY_DATA"
    }
}

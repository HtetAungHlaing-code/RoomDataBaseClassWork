package com.example.roomwithviewclasswork

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_add_products.*
import kotlinx.android.synthetic.main.activity_get_by_name.*

class GetByName : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_by_name)

        btnSearch.setOnClickListener {
            var replyIntent=Intent()
            if(TextUtils.isEmpty(txt_GetByName.text)){
                txt_GetByName.setError("Enter Product Name")
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }
            else{
                var name = txt_GetByName.text.toString()
                replyIntent.putExtra(GetByName.EXTRA_REPLY,name)
                setResult(Activity.RESULT_OK,replyIntent)
                finish()
            }
        }
    }
    companion object {
        const val EXTRA_REPLY="REPLY_DATA"
    }
}

package com.example.roomwithviewclasswork

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_add_products.*
import kotlinx.android.synthetic.main.activity_get_by_price_range.*

class GetByPriceRange : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_by_price_range)

        btnFind.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(txt_min.text) && TextUtils.isEmpty(txt_max.text)) {
                txt_min.setError("Enter Product Min Price")
                txt_max.setError("Enter Product Max Price")
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val array:Array<String> = arrayOf(txt_min.text.toString(),txt_max.text.toString())
                replyIntent.putExtra(GetByPriceRange.EXTRA_REPLY, array)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }
    companion object {
        const val EXTRA_REPLY="REPLY_DATA"
    }
}

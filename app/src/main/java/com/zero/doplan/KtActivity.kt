package com.zero.doplan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.zero.base.IconItem

class KtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)

        val icon = findViewById(R.id.icon_item) as IconItem
        val btn = findViewById(R.id.btn) as Button
        val btnt = findViewById(R.id.btn_t) as Button

        btn.setOnClickListener {
            icon.setSelect(false)
        }

        btnt.setOnClickListener {
            icon.setSelect(true)
        }
    }
}

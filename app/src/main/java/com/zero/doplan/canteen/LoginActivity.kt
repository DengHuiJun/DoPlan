package com.zero.doplan.canteen

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.zero.doplan.R
import com.zero.doplan.kt.BaseActionBarActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTitleText("食堂订餐系统")

        Glide.with(this).load(R.drawable.ssbg).into(bgIv)

        loginBtn.setOnClickListener({
            val un = useNameEt.text.toString()
            val pwd = pwdEt.text.toString()

            if ("admin" == un && "123" == pwd) {
                goMain()
            }
        })
    }

    private fun goMain() {
        startActivity(Intent(this, CanteenActivity::class.java))
    }
}

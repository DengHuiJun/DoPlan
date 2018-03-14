package com.zero.doplan.canteen

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.zero.doplan.AppContext
import com.zero.doplan.R
import com.zero.doplan.kt.BaseActionBarActivity
import com.zero.doplan.util.ToastUtil
import com.zero.room.Injection
import com.zero.room.PlanDataSource
import com.zero.room.PlanDatabase
import com.zero.room.dao.UserDao
import com.zero.room.entity.User
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTitleText("食堂订餐系统")

        Glide.with(this).load(R.drawable.ssbg).into(bgIv)

        val p = Injection.provideUserDataSource(this)

        loginBtn.setOnClickListener({
            val un = useNameEt.text.toString()
            val pwd = pwdEt.text.toString()

//            Observable.create()

            val id = p.checkLogin(un, pwd)

            if (id < 0) {
                ToastUtil.showShort("用户名或密码错误！")
            } else {
                AppContext.sUserId = id
                goMain()
            }
        })
    }

    private fun goMain() {
        startActivity(Intent(this, CanteenActivity::class.java))
    }
}

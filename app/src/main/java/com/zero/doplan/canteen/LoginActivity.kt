package com.zero.doplan.canteen

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.zero.doplan.AppContext
import com.zero.doplan.R
import com.zero.doplan.util.SharePreferencesUtils
import com.zero.doplan.util.ToastUtil
import com.zero.room.Injection
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTitleText("订餐宝")

        Glide.with(this).load(R.drawable.ssbg).into(bgIv)

        val dun = SharePreferencesUtils.getString("username", "")
        val dpwd = SharePreferencesUtils.getString("pwd", "")
        useNameEt.setText(dun)
        pwdEt.setText(dpwd)

        loginBtn.setOnClickListener({
            val un = useNameEt.text.toString()
            val pwd = pwdEt.text.toString()

            Observable.create(ObservableOnSubscribe<Long> {
                val uid = Injection.provideUserDataSource(this).checkLogin(un, pwd)
                it.onNext(uid)
                it.onComplete()
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it < 0) {
                            ToastUtil.showShort("用户名或密码错误！")


                            SharePreferencesUtils.putString("username", "")
                            SharePreferencesUtils.putString("pwd", "")

                        } else {
                            // 默认记住密码
                            SharePreferencesUtils.putString("username", un)
                            SharePreferencesUtils.putString("pwd", pwd)

                            AppContext.sUserId = it
                            goMain()
                        }
                    }, {
                        ToastUtil.showShort("系统异常！")
                    })
        })
    }

    private fun goMain() {
        startActivity(Intent(this, CanteenActivity::class.java))
    }
}

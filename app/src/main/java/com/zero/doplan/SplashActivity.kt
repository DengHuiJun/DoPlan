package com.zero.doplan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import com.zero.doplan.util.LogUtil

/**
 * Created by Allen.D on 17/2/26.
 */

class SplashActivity : AppCompatActivity() {

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LogUtil.d(TAG + "onCreate")
        mHandler.postDelayed({
            goMainActivity()
            finish()
        }, 400L)
    }

    private fun goMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {

        private val TAG = SplashActivity::class.java.simpleName
    }
}

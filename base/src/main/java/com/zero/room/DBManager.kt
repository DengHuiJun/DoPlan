package com.zero.room

import android.arch.persistence.room.Room
import android.content.Context
import com.zero.room.entity.User

/**
 * Created by hui_deng on 2018/3/14.
 */
object DBManager {
    lateinit var db : CanteenDatabase

    fun init(context : Context) {
        db = Room.databaseBuilder(context.applicationContext,
                CanteenDatabase::class.java, "canteen.db")
                .allowMainThreadQueries()
                .build()
    }

    fun firstInit() {
        db.userDao().insert(getUser("admin", "123"))
    }

    private fun getUser(name : String, pwd : String) : User {
        val u = User()
        u.username = name
        u.pwd = pwd
        val t = System.currentTimeMillis()
        u.createdTime = t
        u.lastUpdateTime = t
        return u
    }
}
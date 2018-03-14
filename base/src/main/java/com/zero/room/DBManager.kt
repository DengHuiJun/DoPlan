package com.zero.room

import android.arch.persistence.room.Room
import android.content.Context
import com.zero.base.DBConstants.*
import com.zero.base.MenuPhotoHelper.*
import com.zero.room.entity.Menu
import com.zero.room.entity.User

/**
 * Created by hui_deng on 2018/3/14.
 */
object DBManager {
    lateinit var db : CanteenDatabase

    fun init(context : Context) {
        db = Room.databaseBuilder(context.applicationContext,
                CanteenDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
    }

    fun firstInit() {
        db.userDao().insert(getUser("admin", "123"))

        // 1号食堂
//        db.menuDao().insert(getMenu(ONE_CANTEEN, TYPE_MORN, "菜包", 1.0, ""))
        db.menuDao().insert(getMenu(ONE_CANTEEN, TYPE_MORN, "肉包", 1.5, ONE_M_ROUBAO))

        db.menuDao().insert(getMenu(ONE_CANTEEN, TYPE_NOON, "牛肉炒饭", 7.0, NIU_ROU_CHAO_FAN))
        db.menuDao().insert(getMenu(ONE_CANTEEN, TYPE_NOON, "蛋炒饭", 5.5, TWO_N_CHAOFAN))

//        db.menuDao().insert(getMenu(ONE_CANTEEN, TYPE_NIGHT, "米粉", 6.0, ""))
        db.menuDao().insert(getMenu(ONE_CANTEEN, TYPE_NIGHT, "西红柿炒蛋", 5.0, ONE_NN_XIHONGSHI))

        // 2号

        db.menuDao().insert(getMenu(TWO_CANTEEN, TYPE_MORN, "油条", 1.0, TWO_M_ROUBAO))

        db.menuDao().insert(getMenu(TWO_CANTEEN, TYPE_NOON, "鸡腿饭", 10.0, ONE_N_JITUI))

        db.menuDao().insert(getMenu(TWO_CANTEEN, TYPE_NIGHT, "土豆丝", 5.0, TWO_NN_TUDOUSI))


        // 3号
        db.menuDao().insert(getMenu(THREE_CANTEEN, TYPE_MORN, "豆沙包", 1.0, ""))

        db.menuDao().insert(getMenu(THREE_CANTEEN, TYPE_NOON, "宫保鸡丁", 12.0, ""))

        db.menuDao().insert(getMenu(THREE_CANTEEN, TYPE_NIGHT, "白米粥", 3.0, ""))


        // 4号
        db.menuDao().insert(getMenu(FOUR_CANTEEN, TYPE_MORN, "黑芝麻饼", 1.0, ""))

        db.menuDao().insert(getMenu(FOUR_CANTEEN, TYPE_NOON, "烧茄子", 8.0, ""))

        db.menuDao().insert(getMenu(FOUR_CANTEEN, TYPE_NIGHT, "牛肉米粉", 6.0, ""))

        // 5号
        db.menuDao().insert(getMenu(FIVE_CANTEEN, TYPE_MORN, "馄饨", 6.0, ""))

        db.menuDao().insert(getMenu(FIVE_CANTEEN, TYPE_NOON, "剁椒鱼头", 10.0, ""))

        db.menuDao().insert(getMenu(FIVE_CANTEEN, TYPE_NIGHT, "鸡肉炒饭", 6.0, ""))

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

    private fun getMenu(canteenId : Int,  type: Int, name: String, price: Double, imgKey: String): Menu {
        val m = Menu()
        m.canteenId = canteenId
        m.type = type
        m.name = name
        m.price = price
        m.imgKey = imgKey
        val t = System.currentTimeMillis()
        m.createdTime = t
        m.lastUpdateTime = t
        return m
    }
}
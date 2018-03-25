package com.zero.room

import android.arch.persistence.room.Room
import android.content.Context
import com.zero.room.entity.Plan

/**
 * Created by zerogeek on 2018/3/25.
 */
object DBManager {
    lateinit var db : PlanDatabase

    fun init(context : Context) {
        db = Room.databaseBuilder(context.applicationContext,
                PlanDatabase::class.java, "do_plan.db")
                .allowMainThreadQueries()
                .build()
    }

    fun firstInit() {
        db.planDao().insertPlan(getPlan(100,"这是一个示例,阅读《迷宫》",1521986865000,1522986865000))
        db.planDao().insertPlan(getPlan(200,"这是一个示例,阅读《乌合之众》",1521986865000,1522986865000))
        db.planDao().insertPlan(getPlan(200,"这是一个示例,阅读《乌合之众》",1521986865000,1522986865000))
        db.planDao().insertPlan(getPlan(200,"这是一个示例,阅读《乌合之众》",1521986865000,1522986865000))
        db.planDao().insertPlan(getPlan(200,"这是一个示例,阅读《乌合之众》",1521986865000,1522986865000))
        db.planDao().insertPlan(getPlan(200,"这是一个示例,阅读《乌合之众》",1521986865000,1522986865000))
        db.planDao().insertPlan(getPlan(200,"这是一个示例,阅读《乌合之众》",1521986865000,1522986865000))
        db.planDao().insertPlan(getPlan(200,"这是一个示例,阅读《乌合之众》",1521986865000,1522986865000))

    }

    private fun getPlan(g:Int, c:String, b: Long, e:Long): Plan {
        val p = Plan()
        val t = System.currentTimeMillis()
        p.let {
            it.createdTime = t
            it.lastUpdateTime = t
            it.isHasDone = false
            it.planType = 0
            it.goals = g
            it.content = c
            it.endTime = e
            it.startTime = b
        }
        return p
    }

}
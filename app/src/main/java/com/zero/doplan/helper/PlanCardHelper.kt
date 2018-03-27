package com.zero.doplan.helper

/**
 * Created by zerogeek on 2018/3/27.
 */
object PlanCardHelper {

    fun getTypeLabel(type : Int) : String {
        when(type) {
            1 -> return "读书"
            else -> return "常规"
        }
    }
}
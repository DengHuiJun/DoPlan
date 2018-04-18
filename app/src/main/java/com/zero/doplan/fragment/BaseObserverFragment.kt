package com.zero.doplan.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

import com.zero.doplan.event.EventObserver
import com.zero.doplan.event.EventsObservable
import com.zero.doplan.event.NotificationCenter

import java.lang.ref.WeakReference

/**
 * Created by Allen.D on 17/4/8.
 */

abstract class BaseObserverFragment : Fragment() {

    private lateinit var mObserver: FragmentEventObserver
    protected lateinit var mContext: Context

    protected fun getMyGroup(): String {
        return NotificationCenter.DEFAULT_GROUP
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mObserver = FragmentEventObserver(this)
        registerEventObserver(mObserver)

        mContext = activity
    }

    override fun onDestroy() {
        unregisterEventObserver(mObserver)
        super.onDestroy()
    }

    private fun registerEventObserver(eventObserver: EventObserver) {
        val observerEventTypes = getObserverEventType()
        if (observerEventTypes != null) {
            val eventsObservable = EventsObservable.getInstance()
            for (eventType in observerEventTypes) {
                eventsObservable.registerObserver(eventType, eventObserver)
            }
        }
    }

    private fun unregisterEventObserver(eventObserver: EventObserver?) {
        val observerEventTypes = getObserverEventType()
        if (observerEventTypes != null) {
            val eventsObservable = EventsObservable.getInstance()
            for (eventType in observerEventTypes) {
                eventsObservable.unregisterObserver(eventType, eventObserver)
            }
        }
    }

    private class FragmentEventObserver(fragment: BaseObserverFragment) : EventObserver() {
        private val mFragment: WeakReference<BaseObserverFragment>

        init {
            mFragment = WeakReference(fragment)
        }

        override fun onChange(eventType: String, eventArgs: Bundle) {
            val fragment = mFragment.get()
            fragment?.onChange(eventType, eventArgs)
        }

        override fun getGroup(): String {
//            val fragment = mFragment.get()
            return NotificationCenter.DEFAULT_GROUP
        }
    }

    protected abstract fun onChange(eventType: String, eventArgs: Bundle)

    /**
     * 实现返回需要观察者监听的业务事件类型
     *
     * @return 该界面所监听的事件类型
     */
    protected abstract fun getObserverEventType(): Array<String>
}

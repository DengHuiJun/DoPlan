package com.zero.doplan.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

/**
 * 模拟自动点击安装
 * Created by hui_deng on 2018/3/28.
 */
class AccessService : AccessibilityService() {

    private val handleMap = HashMap<Int, Boolean>()

    override fun onInterrupt() {
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        val nodeInfo = event.source
        if (nodeInfo != null) {
            if (event.eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED ||
                    event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                if (handleMap[event.windowId] == null) {
                    val handled = iterateNodesAndHandle(nodeInfo)
                    if (handled) {
                        handleMap.put(event.windowId, true)
                    }
                }
            }

        }
    }

    private fun iterateNodesAndHandle(info : AccessibilityNodeInfo) : Boolean {
        val childCount = info.childCount
        if ("android.widget.Button" == info.className) {
            val btnStr = info.text.toString()
            if ("安装" == btnStr || "打开" == btnStr) {
                info.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                return true
            }
        } else if ("android.widget.ScrollView" == info.className) {
            info.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)
        }

        for (i in 0 until childCount) {
            val childNodeInfo = info.getChild(i)
            if (iterateNodesAndHandle(childNodeInfo)) {
                return true
            }
        }
        return false
    }
}
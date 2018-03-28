package com.zero.doplan.helper

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.text.TextUtils
import java.io.File

/**
 * Created by hui_deng on 2018/3/28.
 */
object UpdateApkHepler {

    fun installApk(context: Context, apkPath: String) {
        if (TextUtils.isEmpty(apkPath)) {
            return
        }

        val file = File(apkPath)
        val intent = Intent(Intent.ACTION_VIEW)

        // 判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            // provider authorities
            val apkUri = FileProvider.getUriForFile(context, "com.zero.doplan.fileprovider", file)
            // Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
        }
        context.startActivity(intent)
    }
}
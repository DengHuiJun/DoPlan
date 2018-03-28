package com.zero.doplan.helper

import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * Created by hui_deng on 2018/3/28.
 */
object SDHepler {
    val path = Environment.getExternalStorageDirectory().absolutePath  + "/.doplan/"

    fun inputStreamToFile(ins: InputStream, file: File) {
        val os = FileOutputStream(file)
        var bytesRead : Int
        val buffer = ByteArray(8192)
        bytesRead = ins.read(buffer, 0, 8192)
        while (bytesRead != -1) {
            os.write(buffer, 0, bytesRead)
            bytesRead = ins.read(buffer, 0, 8192)
        }
        os.close()
        ins.close()
    }
}
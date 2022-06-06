package com.whynotpot.third_party_module_manager.file

import android.content.Context
import android.util.Log
import java.io.File

enum class FilePathType {
    ABSOLUTE,
    APP_FOLDER

}

class FileLoaderImpl {
    companion object {
        fun get(type: FilePathType, filePath: String = "none", context: Context): File {
            return when (type) {
                FilePathType.APP_FOLDER -> context.getExternalFilesDir("modals")!!
                FilePathType.ABSOLUTE -> {
                    val path = context.getExternalFilesDir(filePath)
                    if (path != null) {
                        Log.e(this::class.simpleName, "Not found")
                        return path
                    } else {
                        context.getExternalFilesDir("modals")!!
                    }

                }
                else -> context.getExternalFilesDir("modals")!!
            }
        }
    }
}
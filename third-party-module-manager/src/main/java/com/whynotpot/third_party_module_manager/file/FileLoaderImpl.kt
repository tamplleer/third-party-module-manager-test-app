package com.whynotpot.third_party_module_manager.file

import android.content.Context
import java.io.File

enum class FilePathType {
    ABSOLUTE,
    APP_FOLDER

}

class FileLoaderImpl {
    companion object {
        fun get(type: FilePathType, fileName: String, context: Context): File? {
            return when (type) {
                FilePathType.APP_FOLDER -> context.getExternalFilesDir("modals")
                else -> null
            }
        }
    }
}
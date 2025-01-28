package it.unina.dietiestates.utils

import android.content.Intent
import android.os.Build
import java.io.Serializable

inline fun <reified T : Serializable> getSerializable(intent: Intent, key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        intent.getSerializableExtra(key) as? T
    }
}

package it.unina.dietiestates.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

object ImageUtils {
    fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            null
        }
    }

    fun encodeBitmapToBase64(bitmap: Bitmap, format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100): String {
        return try {
            val outputStream = ByteArrayOutputStream()
            // Comprime il bitmap e scrive i dati nell'output stream
            bitmap.compress(format, quality, outputStream)
            val byteArray = outputStream.toByteArray()
            outputStream.close()
            // Converte i byte in una stringa Base64
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun resizeAndCompressBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int, quality: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        // Calculate scale factor
        val scaleFactor = minOf(maxWidth.toFloat() / width, maxHeight.toFloat() / height)
        // Resize the bitmap
        val resizedBitmap = Bitmap.createScaledBitmap(
            bitmap,
            (width * scaleFactor).toInt(),
            (height * scaleFactor).toInt(),
            true
        )
        // Compress the resized bitmap
        val outputStream = ByteArrayOutputStream()
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)

        return BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size())
    }
}
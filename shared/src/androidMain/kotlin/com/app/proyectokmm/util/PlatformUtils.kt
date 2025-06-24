package com.app.proyectokmm.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

actual fun currentTimeMillis(): Long = System.currentTimeMillis()

actual fun md5(string: String): String {
    val MD5 = "MD5"
    try {
        val digest = MessageDigest.getInstance(MD5)
        digest.update(string.toByteArray())
        val messageDigest = digest.digest()

        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}

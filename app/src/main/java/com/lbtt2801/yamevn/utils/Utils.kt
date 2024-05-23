package com.lbtt2801.yamevn.utils

class Utils {
    companion object {
        fun checkSamePassword(oldPass: String, newPass: String): Boolean {
            return oldPass.equals(newPass, ignoreCase = false)
        }
    }
}
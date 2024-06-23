package com.lbtt2801.yamevn.utils

import java.text.NumberFormat
import java.util.Locale

class Utils {
    companion object {
        fun checkSamePassword(oldPass: String, newPass: String): Boolean {
            if (oldPass.isEmpty()) return  false
            if (newPass.isEmpty()) return  false
            return oldPass.equals(newPass, ignoreCase = false)
        }

        fun formattedAmount(price: Int) : String {
            return NumberFormat.getNumberInstance(Locale("vi")).format(price)
        }

        fun isPhoneNumberValid(phoneNumber: String): Boolean {
            val pattern = Regex("^(\\+?84|0[3|5|7|8|9])+([0-9]{8})\\b")
            return pattern.matches(phoneNumber)
        }

        fun detailReturnProduct(status: Int) : String {
            return when (status) {
                8 -> "Yêu cầu trả hàng"
                9 -> "Yêu cầu đổi hàng"
                10 -> "Đã Xác nhận yêu cầu trả hàng"
                11 -> "Đã Xác nhận yêu cầu đổi hàng"
                12 -> "Đơn vị vận chuyển đang đến nhận hàng đổi/trả"
                13 -> "Đã trả hàng"
                14 -> "Đã đổi hàng"
                15 -> "Không chấp nhận yêu cầu trả hàng"
                16 -> "Không chấp nhận yêu cầu đổi hàng"
                else -> "Yêu cầu trả hàng"
            }
        }
    }
}
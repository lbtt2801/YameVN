package com.lbtt2801.yamevn.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lbtt2801.yamevn.models.ProductCart

fun saveCartToSharedPreferences(context: Context, cartItems: List<ProductCart>) {
    val sharedPreferences = context.getSharedPreferences("Cart", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val gson = Gson()
    val cartJson = gson.toJson(cartItems)

    editor.putString("cartItems", cartJson)
    editor.apply()
}

fun getCartFromSharedPreferences(context: Context): List<ProductCart> {
    val sharedPreferences = context.getSharedPreferences("Cart", Context.MODE_PRIVATE)
    val cartJson = sharedPreferences.getString("cartItems", null)

    val gson = Gson()
    val cartItemsType = object : TypeToken<List<ProductCart>>() {}.type

    return gson.fromJson(cartJson, cartItemsType) ?: emptyList()
}
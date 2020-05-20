/*
 * Copyright © 2020 LaLiga.
 * All rights reserved.
 */

package com.etologic.elrefugioapp.utils

import android.content.Context
import android.util.Base64
import com.google.gson.Gson
import java.io.Serializable

object SharedPreferencesUtils {
    
    private const val FILE_NAME = "elrefugio_shared_prefs"
    
    @Throws(ClassCastException::class)
    internal fun <T> getData(context: Context?, key: String, classOfT: Class<T>): T? {
        val decryptedData = getData(context, key)
        val gson = Gson()
        return gson.fromJson(decryptedData, classOfT)
    }
    
    @Throws(ClassCastException::class)
    private fun getData(context: Context?, key: String): String {
        val sp = context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val data = sp?.getString(key, "")
        return decrypt(data)
    }
    
    private fun decrypt(input: String?): String = try {
        String(Base64.decode(input, Base64.DEFAULT))
    } catch (e: Exception) {
        ""
    }
    
    internal fun storeData(context: Context?, key: String, value: Serializable) {
        val gson = Gson()
        val valueString = gson.toJson(value)
        val encryptedValue = encrypt(valueString)
        val sp = context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val edit = sp?.edit()
        edit?.putString(key, encryptedValue)
        edit?.apply()
    }
    
    private fun encrypt(input: String): String =
        Base64.encodeToString(input.toByteArray(), Base64.DEFAULT)
    
    internal fun clearData(context: Context?, key: String) {
        val sp = context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp?.edit()
        editor?.remove(key)
        editor?.apply()
    }
    
    @Throws(ClassCastException::class)
    internal fun <T> getData(context: Context?, key: String, classOfT: Class<T>, def: T): T {
        val decryptedData = getData(context, key, encrypt(def.toString()))
        val gson = Gson()
        return gson.fromJson(decryptedData, classOfT)
    }
    
    @Throws(ClassCastException::class)
    private fun getData(context: Context?, key: String, def: String): String {
        val sp = context?.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val data = sp?.getString(key, def)
        return decrypt(data)
    }
}

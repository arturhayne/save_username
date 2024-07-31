package br.htech.saveusername

import android.content.Context

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun getUserName(): String? {
        return sharedPreferences.getString("user_name", null)
    }

    fun setUserName(userName: String) {
        sharedPreferences.edit().putString("user_name", userName).apply()
    }
}

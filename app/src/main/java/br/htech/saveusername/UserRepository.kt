package br.htech.saveusername

class UserRepository(private val sharedPreferencesHelper: SharedPreferencesHelper) {

    fun getUserName(): String? {
        return sharedPreferencesHelper.getUserName()
    }

    fun setUserName(userName: String) {
        sharedPreferencesHelper.setUserName(userName)
    }
}

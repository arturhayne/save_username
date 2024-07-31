package br.htech.saveusername

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> get() = _userName

    init {
        _userName.value = userRepository.getUserName()
    }

    fun setUserName(userName: String) {
        userRepository.setUserName(userName)
        _userName.value = userName
    }
}

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


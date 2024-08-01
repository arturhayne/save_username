package br.htech.saveusername

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userPreferences: LiveData<UserPreferences> = userRepository.userPreferences
        .asLiveData()

    fun setUserName(userName: String) {
        viewModelScope.launch {
            userRepository.setUserName(userName)
        }
    }

    fun setEmail(email: String) {
        viewModelScope.launch {
            userRepository.setEmail(email)
        }
    }
}

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

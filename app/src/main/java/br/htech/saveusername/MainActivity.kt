package br.htech.saveusername

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepository = UserRepository(this)
        val viewModelFactory = UserViewModelFactory(userRepository)

        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        setContent {
            MyApp(userViewModel)
        }
    }
}

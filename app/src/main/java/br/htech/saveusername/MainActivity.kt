package br.htech.saveusername

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import br.htech.saveusername.ui.theme.SaveUsernameTheme

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferencesHelper = SharedPreferencesHelper(this)
        val userRepository = UserRepository(sharedPreferencesHelper)
        val viewModelFactory = UserViewModelFactory(userRepository)

        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        setContent {
            MyApp(userViewModel)
        }
    }
}

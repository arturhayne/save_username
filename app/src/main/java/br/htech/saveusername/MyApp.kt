package br.htech.saveusername

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyApp(userViewModel: UserViewModel) {
    val userName by userViewModel.userName.observeAsState()
    var textFieldValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = { Text("Enter user name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            userViewModel.setUserName(textFieldValue)
        }) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = userName ?: "No user name saved")
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    val fakeSharedPreferencesHelper = SharedPreferencesHelper(context = LocalContext.current)
    val fakeUserRepository = UserRepository(fakeSharedPreferencesHelper)
    val fakeUserViewModel = UserViewModel(fakeUserRepository)

    MyApp(userViewModel = fakeUserViewModel)
}

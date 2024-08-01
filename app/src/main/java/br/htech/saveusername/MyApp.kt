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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyApp(userViewModel: UserViewModel) {
    val userPreferences by userViewModel.userPreferences.observeAsState(UserPreferences.getDefaultInstance())
    MyAppContent(
        userPreferences,
        { username: String -> userViewModel.setUserName(username) },
        { email: String -> userViewModel.setEmail(email) }
    )
}
@Composable
fun MyAppContent(
    userPreferences: UserPreferences,
    setUserName: (String) ->Unit,
    setEmail: (String) ->Unit,
) {
    var userNameInput by remember { mutableStateOf(userPreferences.userName) }
    var emailInput by remember { mutableStateOf(userPreferences.email) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = userNameInput,
            onValueChange = { userNameInput = it },
            label = { Text("Enter user name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = emailInput,
            onValueChange = { emailInput = it },
            label = { Text("Enter email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            setUserName(userNameInput)
            setEmail(emailInput)
        }) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = userPreferences.userName ?: "No user name saved")
        Text(text = userPreferences.email ?: "No email saved")
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppContentPreview() {
    val previewUserPreferences = UserPreferences.newBuilder()
        .setUserName("Preview User")
        .setEmail("preview@example.com")
        .build()

    MyAppContent(
        userPreferences = previewUserPreferences,
        setUserName = {},
        setEmail = {}
    )
}


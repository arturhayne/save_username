package br.htech.saveusername

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

private val Context.dataStore: DataStore<UserPreferences> by dataStore(
    fileName = "user_prefs.pb",
    serializer = UserPreferencesSerializer
)

class UserRepository(private val context: Context) {

    val userPreferences: Flow<UserPreferences> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun setUserName(userName: String) {
        context.dataStore.updateData { preferences ->
            preferences.toBuilder().setUserName(userName).build()
        }
    }

    suspend fun setEmail(email: String) {
        context.dataStore.updateData { preferences ->
            preferences.toBuilder().setEmail(email).build()
        }
    }
}

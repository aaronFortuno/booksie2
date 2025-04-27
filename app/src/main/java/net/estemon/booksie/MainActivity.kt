package net.estemon.booksie

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowCompat
import net.estemon.booksie.ui.login.ui.LoginScreen
import net.estemon.booksie.ui.theme.BooksieTheme
import net.estemon.booksie.ui.theme.rememberThemeManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeManager = rememberThemeManager()
            val isDarkMode by themeManager.isDarkMode.collectAsState()

            BooksieTheme(darkTheme = isDarkMode) {
                LoginScreen()
            }
        }
    }
}
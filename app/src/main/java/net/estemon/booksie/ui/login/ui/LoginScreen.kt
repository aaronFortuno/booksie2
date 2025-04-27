package net.estemon.booksie.ui.login.ui

import android.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.estemon.booksie.R
import net.estemon.booksie.ui.theme.ThemeManager
import net.estemon.booksie.ui.theme.rememberThemeManager

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val scope = rememberCoroutineScope()
            val themeManager = rememberThemeManager()
            val isDarkMode by themeManager.isDarkMode.collectAsState()

            Spacer(modifier = Modifier.weight(1f))
            HeaderImage()
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField()
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField()
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton()
            ThemeButton(scope, themeManager, isDarkMode)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun ThemeButton(
    scope: CoroutineScope,
    themeManager: ThemeManager,
    isDarkMode: Boolean
) {
    IconButton(
        onClick = {
            scope.launch {
                themeManager.setDarkMode(!isDarkMode)
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = if (isDarkMode)
                painterResource(R.drawable.light_mode)
            else
                painterResource(R.drawable.dark_mode),
            contentDescription = if (isDarkMode)
                stringResource(R.string.light_mode)
            else
                stringResource(R.string.dark_mode),
            tint = colorScheme.onPrimaryContainer,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun HeaderImage() {
    val isDark = isSystemInDarkTheme()
    Icon(
        painter = painterResource(id = R.drawable.logo_booksie),
        contentDescription = null,
        tint = if (isDark) colorScheme.onSurface else colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EmailField() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Email")
        },
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun PasswordField() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Password")
        },
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun LoginButton() {
    //
}

package net.estemon.booksie.ui.login.ui

import android.graphics.ColorFilter
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    val scope = rememberCoroutineScope()

    val themeManager = rememberThemeManager()
    val isDarkMode by themeManager.isDarkMode.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isKeyboardVisible by remember { mutableStateOf(false) }

    val logoSize by animateFloatAsState(
        targetValue = if (isKeyboardVisible) 0.4f else 1f,
        label = "logoSize"
    )

    val topSpacerWeight by animateFloatAsState(
        targetValue = if (isKeyboardVisible) 0.1f else 1f,
        label = "topSpacerWeight"
    )

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


            Spacer(modifier = Modifier.weight(topSpacerWeight))
            HeaderImage(
                modifier = Modifier.scale(logoSize)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(
                value = email,
                onValueChange = { email = it },
                onFocusChanged = { focused -> isKeyboardVisible = focused }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(
                value = password,
                onValueChange = { password = it },
                onFocusChanged = { focused -> isKeyboardVisible = focused }
            )
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton()
            ThemeButton(scope, themeManager, isDarkMode)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun HeaderImage(
    modifier: Modifier
) {
    val isDark = isSystemInDarkTheme()
    Icon(
        painter = painterResource(id = R.drawable.logo_booksie),
        contentDescription = null,
        tint = colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit
) {
    var fieldFocus by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                fieldFocus = it.isFocused
                onFocusChanged(it.isFocused)
            },
        placeholder = { Text("Email") },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                tint = colorScheme.primary
            )
        }
    )
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit
) {
    var fieldFocus by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                fieldFocus = it.isFocused
                onFocusChanged(it.isFocused)
            },
        placeholder = {
            Text("Password")
        },
        singleLine = true,
        maxLines = 1,
        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = colorScheme.primary
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                Icon(
                    imageVector = if (passwordVisible)
                        Icons.Default.VisibilityOff
                    else
                        Icons.Default.Visibility,
                    contentDescription = if (passwordVisible)
                        "Ocultar contraseña"
                    else
                        "Mostrar contraseña",
                    tint = colorScheme.primary
                )
            }
        }
    )
}

@Composable
fun LoginButton() {
    //
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
            tint = colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
    }
}

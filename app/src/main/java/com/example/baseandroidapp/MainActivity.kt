package com.example.baseandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.baseandroidapp.presentation.users.UsersScreen
import com.example.baseandroidapp.ui.theme.BaseAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseAndroidAppTheme {
                AppRoot()
            }
        }
    }
}

@Composable
private fun AppRoot() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        UsersScreen(modifier = Modifier.padding(innerPadding))
    }
}

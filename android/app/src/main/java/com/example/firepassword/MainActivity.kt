package com.example.firepassword

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aheaditec.talsec_security.security.api.Talsec
import com.example.firepassword.ui.navigation.NavigationGraph
import com.example.firepassword.ui.theme.FirePasswordTheme
import com.example.firepassword.ui.theme.TestTheme.AppThemeType
import com.example.firepassword.ui.theme.TestTheme.MultiThemeApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            FirePasswordTheme {
//                val navController = rememberNavController()
//                NavigationGraph( navController = navController, this ) } }
//        Talsec.blockScreenCapture(this,true)

            var currentTheme by remember { mutableStateOf(AppThemeType.LIGHT) }

            MultiThemeApp(themeType = currentTheme) {


                ExploitScreen(

                    onRunExploit = {
                        ExploitBridge.runExploit()
                    },

                    onGetSystemInfo = {
                        ExploitBridge.getSystemInfo()
                    }

                )


//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Column {
//                        Text(text = "Current Theme: ${currentTheme.name}")
//
//                         Buttons to alter selection status programmatically
//                        Button(onClick = { currentTheme = AppThemeType.LIGHT }) { Text("Light") }
//                        Button(onClick = { currentTheme = AppThemeType.DARK }) { Text("Dark") }
//                        Button(onClick = { currentTheme = AppThemeType.BLUE_OCEAN }) { Text("Blue Ocean") }
//                        Button(onClick = { currentTheme = AppThemeType.PINK_FLAMINGO }) { Text("Pink Flamingo") }
//                    }
//                }
            }
        }




    }
}

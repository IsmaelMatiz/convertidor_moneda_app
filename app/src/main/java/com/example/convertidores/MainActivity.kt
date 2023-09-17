package com.example.convertidores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.convertidores.AppActivities.MyExposedDropdownMenuBox
import com.example.convertidores.AppActivities.standardScreen
import com.example.convertidores.ui.theme.ConvertidoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertidoresTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EntryActivity()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ConvertidoresTheme {
        EntryActivity()
    }
}

@Composable
fun EntryActivity(){
    val context = LocalContext.current
    standardScreen(backgroundPic = painterResource(id = R.drawable.backgroundapp),
        iconPic = painterResource(id = R.drawable.logo_currency),
        Color(0xFF00b3b3))
    MyExposedDropdownMenuBox(title = "Elije El convertidos", options = listOf("Convertidor de binarios", "Convertidor de moneda"), colorDropDown = Color(0xFF00b3b3)) {

    }
}


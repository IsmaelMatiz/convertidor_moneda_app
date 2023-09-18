package com.example.convertidores.AppActivities.CurrencyConverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.convertidores.AppActivities.ActivityEnterValue
import com.example.convertidores.R
import com.example.convertidores.ui.theme.ConvertidoresTheme

class EnterValueActivity : ComponentActivity() {
    val bundle = intent.extras
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            ConvertidoresTheme {
                EnterValueActivityView()
            }
        }
    }
    
    @Composable
    fun EnterValueActivityView(){
        ActivityEnterValue(
            converter = bundle?.getString("converter").toString(),
            wayToConvert = bundle?.getString("wayToConvert").toString(),
            title = "Ingresa el valor a convertir",
            colorDropDown = Color(0xFF00b3b3)
        )
    }
}
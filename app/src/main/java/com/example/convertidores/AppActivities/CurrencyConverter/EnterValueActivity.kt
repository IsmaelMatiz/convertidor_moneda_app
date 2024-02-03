package com.example.convertidores.AppActivities.CurrencyConverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.convertidores.AppActivities.ActivityEnterValue
import com.example.convertidores.R
import com.example.convertidores.logic.currency.CurAPICall
import com.example.convertidores.ui.theme.ConvertidoresTheme


    @Composable
    fun EnterValueActivityView(ConvertOperation: String, converter: String){
            ActivityEnterValue(
                converter = converter,
                wayToConvert = ConvertOperation,
                title = "Ingresa el valor a convertir",
                colorDropDown = Color(0xFF00b3b3)
            )
    }

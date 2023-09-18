package com.example.convertidores.AppActivities.CurrencyConverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.convertidores.AppActivities.ActivityEnterValue
import com.example.convertidores.AppActivities.BinaryConverter.ChoosenBiActivity
import com.example.convertidores.AppActivities.Dropdowns
import com.example.convertidores.AppActivities.standardScreen
import com.example.convertidores.R
import com.example.convertidores.logic.currency.Calculation
import com.example.convertidores.ui.theme.ConvertidoresTheme

class ChoosenRateConverter : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertidoresTheme {
                ChooseRate()
            }
        }
    }

    @Composable
    fun ChooseRate() {
        val context = LocalContext.current
        val converterOptions = listOf(
            "De Pesos a Dolar",
            "De Pesos a Euro",
            "De Pesos a Libras",
            "De Pesos a Yen",
            "De Pesos a Won Coreano",
            "De Dolar a Pesos",
            "De Euro a Pesos",
            "De Libras a Pesos",
            "De Yen a Pesos",
            "De Won Coreano a Pesos"
        )
        standardScreen(
            backgroundPic = painterResource(id = R.drawable.backgroundapp),
            iconPic = painterResource(id = R.drawable.logo_currency),
            tintIconPic = Color(0xFF00b3b3),
            size = 220.dp
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Dropdowns(
                title = "Elije el convertidor",
                options = converterOptions,
                colorDropDown = Color(0xFF00b3b3),
                {
                    val intent = Intent(context,EnterValueActivity::class.java)
                    intent.putExtra("converter","currency")
                    intent.putExtra("wayToConvert",it)
                    context.startActivity(intent)
                })
        }

    }
}
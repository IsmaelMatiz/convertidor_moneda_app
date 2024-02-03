package com.example.convertidores.AppActivities.CurrencyConverter

import android.widget.Toast
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
import com.example.convertidores.AppActivities.Dropdowns
import com.example.convertidores.AppActivities.standardScreen
import com.example.convertidores.R
import com.example.convertidores.logic.currency.CurAPICall


enum class ConverterOptions(val valToShow: String){
    PesosaDolar("De Pesos a Dolar"),
    PesosaEuro("De Pesos a Euro"),
    PesosaLibras("De Pesos a Libras"),
    PesosaYen("De Pesos a Yen"),
    PesosaWon("De Pesos a Won Coreano"),
    DolaraPesos("De Dolar a Pesos"),
    EuroaPesos("De Euro a Pesos"),
    LibrasaPesos("De Libras a Pesos"),
    YenaPesos("De Yen a Pesos"),
    WonaPesos("De Won Coreano a Pesos"),


}

@Composable
    fun ChooseRate(navigateToEnterVal : (String) -> Unit) {
        val context = LocalContext.current
        val converterOptionslist = listOf(
            ConverterOptions.PesosaDolar,
            ConverterOptions.PesosaWon,
            ConverterOptions.PesosaEuro,
            ConverterOptions.PesosaLibras,
            ConverterOptions.PesosaYen,
            ConverterOptions.DolaraPesos,
            ConverterOptions.EuroaPesos,
            ConverterOptions.LibrasaPesos,
            ConverterOptions.YenaPesos,
            ConverterOptions.WonaPesos
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
                title = "Elije la moneda a convertir",
                options = converterOptionslist,
                colorDropDown = Color(0xFF00b3b3)
            ) {
                navigateToEnterVal("${it.valToShow}/currency")
            }
        }

    }
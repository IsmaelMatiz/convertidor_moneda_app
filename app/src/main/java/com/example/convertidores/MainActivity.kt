package com.example.convertidores

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.convertidores.AppActivities.BinaryConverter.ChoosenBiActivity
import com.example.convertidores.AppActivities.CurrencyConverter.ChoosenRateConverter
import com.example.convertidores.AppActivities.Dropdowns
import com.example.convertidores.AppActivities.standardScreen
import com.example.convertidores.logic.currency.CurAPICall
import com.example.convertidores.ui.theme.ConvertidoresTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel = CurrencyViewModel()
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

    class CurrencyViewModel : ViewModel() {
        init {
            viewModelScope.launch {
                // Perform the network operation
                CurAPICall.getInstance().setRates()
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
    val converterOptions = listOf("Convertidor de binarios", "Convertidor de moneda")
    standardScreen(backgroundPic = painterResource(id = R.drawable.backgroundapp),
        iconPic = painterResource(id = R.drawable.logo_currency),
        tintIconPic =  Color(0xFF00b3b3),
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
                if(it.equals(converterOptions[0])){
                    context.startActivity(Intent(context, ChoosenBiActivity::class.java))
                }else{
                    context.startActivity(Intent(context, ChoosenRateConverter::class.java))
                }
            })
    }
}


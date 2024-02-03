package com.example.convertidores

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.convertidores.AppActivities.CurrencyConverter.ChooseRate
import com.example.convertidores.AppActivities.CurrencyConverter.EnterValueActivityView
import com.example.convertidores.AppActivities.Dropdowns
import com.example.convertidores.AppActivities.DropdownsS
import com.example.convertidores.AppActivities.standardScreen
import com.example.convertidores.logic.currency.CurAPICall
import com.example.convertidores.ui.theme.ConvertidoresTheme
import com.example.convertidores.ui.theme.Teal200
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        GlobalScope.launch { CurAPICall.getInstance().setRates() }

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ConvertidoresTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(navController = navController, startDestination = "EntryScreen") {
                        composable("EntryScreen") {
                            EntryActivity({navController.navigate("chooseRateConverter") }
                                , { }) }
                        composable("chooseRateConverter") { ChooseRate(
                            navigateToEnterVal = {navController.navigate("EnterValueConverter/${it}")}
                        ) }
                        composable("EnterValueConverter/{wayToConvert}/{converter}") {
                            backStackEntry ->
                            val wayToConvert = backStackEntry.arguments?.getString("wayToConvert") ?: ""
                            val converterChoosen = backStackEntry.arguments?.getString("converter") ?: ""
                            EnterValueActivityView(wayToConvert,converterChoosen)
                        }
                        // Add more destinations similarly.
                    }

                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun prevMainScreen(){
    EntryActivity(navigateToCurrencyConverter = { /*TODO*/ }
        , navigateToBinaryConverter = {})
}


@Composable
fun EntryActivity(
    navigateToCurrencyConverter: () -> Unit,
    navigateToBinaryConverter: () -> Unit
){
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
        DropdownsS(
            title = "Elije el convertidor",
            options = converterOptions,
            colorDropDown = Color(0xFF00b3b3)
        ) {
            if (it.equals(converterOptions[0])) {
                Toast.makeText(context, "not implemented yet", Toast.LENGTH_SHORT).show()
                //navigateToBinaryConverter()
            } else {
                navigateToCurrencyConverter()
            }
        }
    }
    //Spacer(modifier = Modifier.height(50.dp) )
    
    Row(
        Modifier.fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Pantalla principal"
            , fontSize = 32.sp
            , fontWeight = FontWeight.SemiBold
            , color = Teal200
        )
        Button(onClick = {
            val closeApp = (context as Activity)
            closeApp.finish()
        }
        , colors = ButtonDefaults.
            buttonColors(backgroundColor = Teal200
                        , contentColor = Color.White)
        ) {
            Text(text = "Salir")
        }
    }
    
    
}


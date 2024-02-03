package com.example.convertidores.AppActivities

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.convertidores.AppActivities.CurrencyConverter.ConverterOptions
import com.example.convertidores.R
import com.example.convertidores.logic.currency.Calculation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

//Esta es la version del Screen para mostrar una lista desplegable
@Composable
fun standardScreen(
    backgroundPic: Painter,
    iconPic: Painter,
    tintIconPic: Color,
    size: Dp
){
    Box{
        Image(painter = backgroundPic,
            contentDescription = "App Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = iconPic,
                    contentDescription = "Screen icon",
                    tint = tintIconPic,
                    modifier = Modifier.size(size)
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dropdowns(title: String,
              options: List<ConverterOptions>,
              colorDropDown: Color,
              op: (ConverterOptions) -> Unit){
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded } )
    {
        TextField(
            value = title,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            //colors = ExposedDropdownMenuDefaults.textFieldColors()
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorDropDown.copy(alpha = 0.50f),
                textColor = colorDropDown,
                focusedIndicatorColor = colorDropDown,
                unfocusedIndicatorColor = colorDropDown
            ),
            shape = RoundedCornerShape(topStart =  20.dp, topEnd = 20.dp)
        )
        ExposedDropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(colorDropDown)) {
            for(option in options){
                val scaffoldState : ScaffoldState = rememberScaffoldState()
                val coroutine : CoroutineScope = rememberCoroutineScope()
                DropdownMenuItem(onClick = {
                    op(option)
                }
                ) {
                    Text(
                        text = option.valToShow,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownsS(title: String,
              options: List<String>,
              colorDropDown: Color,
              op: (String) -> Unit){
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded } )
    {
        TextField(
            value = title,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            //colors = ExposedDropdownMenuDefaults.textFieldColors()
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorDropDown.copy(alpha = 0.50f),
                textColor = colorDropDown,
                focusedIndicatorColor = colorDropDown,
                unfocusedIndicatorColor = colorDropDown
            ),
            shape = RoundedCornerShape(topStart =  20.dp, topEnd = 20.dp)
        )
        ExposedDropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(colorDropDown)) {
            for(option in options){
                val scaffoldState : ScaffoldState = rememberScaffoldState()
                val coroutine : CoroutineScope = rememberCoroutineScope()
                DropdownMenuItem(onClick = {
                    op(option)
                }
                ) {
                    Text(
                        text = option,
                    )
                }
            }
        }
    }
}

@Composable
fun ActivityEnterValue(converter: String,wayToConvert : String, title: String, colorDropDown: Color){

    val context = LocalContext.current
    var numToConvert by remember {
        mutableStateOf("")
    }
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
        TextField(
            value = numToConvert,
            placeholder = { Text(text = title) },
            onValueChange = { numToConvert = it },
            //colors = ExposedDropdownMenuDefaults.textFieldColors()
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorDropDown.copy(alpha = 0.50f),
                textColor = colorDropDown,
                focusedIndicatorColor = colorDropDown,
                unfocusedIndicatorColor = colorDropDown
            ),
            shape = RoundedCornerShape(topStart =  20.dp, topEnd = 20.dp)
        )
        Button(onClick = {
            val resultConvertion = CalculateConvertion(numToConvert
            ,converter,wayToConvert)

            when(resultConvertion){
                -1.0f ->
                    Toast.makeText(context,"Solo ingresa numeros!",Toast.LENGTH_SHORT).show()
                -2.0f ->
                    Toast.makeText(context
                        ,"Solo valor absoluto, no numeros negativos!",Toast.LENGTH_SHORT).show()
                -3.0f ->
                    Toast.makeText(context
                        ,"Tenemos inconvenientes intenta mas tarde o " +
                                "contacta a soporte",Toast.LENGTH_SHORT).show()
                else ->
                    Toast.makeText(context
                        ,"El resultado es: $resultConvertion",Toast.LENGTH_LONG).show()
            }

        }) {
            Text(text = "Calcular")
        }
    }
}

/**
 * This function do the require verifications before calculate the converted value
 * currency conversion or Binary both of them.
 * if everything goes right show return the converted value
 * if the numToConvert is not only composed by nums returns -1.0f
 * if the numToConvert has negativeNumbers return -2.0f
 * if something went wrong with the Calculation Java logic -3.0f
 * @param numToconvert String that shoul only contain numbers with the value to be converted
 * @param converter this function take care binary and currency convertion specify wich one
 * @param wayToConvert you can convert Pesos to other currencies and viceversa also with binary
 * especify wich one
 */
fun CalculateConvertion(numToConvert:String, converter: String, wayToConvert: String): Float{
    var resultConvertion = 0.0f
    var castNumToConvert: Float
    try {
        castNumToConvert = numToConvert.toFloat()
    }catch (e:Exception){
        return -1.0f
    }

    if (castNumToConvert < 0) return -2.0f

    if (converter.equals("binary")){
        //execute the binary conversion logic
    }else{
        //execute the currency conversion logic
        resultConvertion = Calculation.CalculateConversion(castNumToConvert,wayToConvert)
    }
    return resultConvertion
}



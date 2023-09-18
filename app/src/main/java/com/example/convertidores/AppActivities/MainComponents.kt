package com.example.convertidores.AppActivities

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.convertidores.AppActivities.CurrencyConverter.ChoosenRateConverter
import com.example.convertidores.R
import kotlinx.coroutines.CoroutineScope

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
fun Dropdowns(title : String,options : List<String>, colorDropDown: Color, op : (String) -> Unit){
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
    var numToConvert = 0
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
            value = title,
            onValueChange = {},
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
            if (converter.equals("binary")){
                //execute the binary conversion logic
            }else{
                //execute the currency conversion logic

            }
        }) {
            Text(text = "Calcular")
        }
    }
}

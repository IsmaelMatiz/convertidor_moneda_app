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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

//Esta es la version del Screen para mostrar una lista desplegable
@Composable
fun standardScreen(
    backgroundPic: Painter,
    iconPic: Painter,
    tintIconPic: Color
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
                    modifier = Modifier.size(220.dp)
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
fun MyExposedDropdownMenuBox(title : String,options : List<String>, colorDropDown: Color, op : () -> Unit){
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
                    op()
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

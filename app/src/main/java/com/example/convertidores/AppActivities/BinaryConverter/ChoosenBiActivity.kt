package com.example.convertidores.AppActivities.BinaryConverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.convertidores.AppActivities.standardScreen
import com.example.convertidores.R
import com.example.convertidores.ui.theme.ConvertidoresTheme

@Composable
 fun chooseBiActivity(){
     standardScreen(backgroundPic = painterResource(id = R.drawable.backgroundapp),
         iconPic = painterResource(id = R.drawable.logo_binario),
         tintIconPic =  Color(0xFF00b3b3),
         size = 420.dp
     )
 }




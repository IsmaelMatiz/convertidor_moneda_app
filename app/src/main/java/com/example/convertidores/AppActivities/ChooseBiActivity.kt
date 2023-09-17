package com.example.convertidores.AppActivities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.convertidores.R
import com.example.convertidores.contentOptions

class ChooseBiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            chooseBiActivity()
        }
    }
}

@Composable
fun chooseBiActivity(){
    standardScreen(backgroundPic = painterResource(id = R.drawable.backgroundapp),
        iconPic = painterResource(id = R.drawable.logo_binario),
        Color(0xFF00b3b3))
}
package com.example.thevetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thevetapp.ui.theme.TheVetAppTheme

class AgregarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun agregarAnimal(){
            var nombre by remember { mutableStateOf("firulais") }
            var edad by remember { mutableStateOf("10") }
            var peso by remember { mutableStateOf("100") }
            Column {
                Text("Nombre $nombre")
                TextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                    }
                )
                Text("Edad $edad")
                TextField(
                    value = edad,
                    onValueChange = {
                        edad = it
                    }
                )
                Text("Peso $peso")
                TextField(
                    value = peso,
                    onValueChange = {
                        peso = it
                    }
                )
                Button(
                    onClick = {
                        animales += Animal(nombre, edad, peso)

                    }
                ) {
                    Text("Agregar animal")
                }
            }
        }
        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TheVetAppTheme {
        Greeting2("Android")
    }
}
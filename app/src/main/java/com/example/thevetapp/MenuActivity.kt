package com.example.thevetapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thevetapp.ui.theme.TheVetAppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle



var animales : List<Animal> = mutableStateListOf(
    Animal("ROCO","10", "13")
)
var animalSeleccionado : Animal = Animal("","","")
class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fun irADetailActivity(){
            var intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        @Composable
        fun listaDeAnimales(nombres : List<Animal>){
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)){
                Text("LISTA DE ANIMALES",
                    modifier = Modifier.padding(vertical = 25.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                nombres.forEach { animal : Animal ->
                    Row (verticalAlignment = Alignment.CenterVertically){
                        listRow(text = animal.nombre)
                        Button(
                            modifier = Modifier.padding(horizontal = 15.dp),
                            onClick = {
                                animalSeleccionado = animal
                                irADetailActivity()

                            }
                        ) {
                            Text(text = "detalles")

                        }
                    }

                }
            }
        }
        @Composable
        fun ListaFinal(listaA : List<Animal>){
            Column {
                listaDeAnimales(listaA)
            }
        }
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun agregarAnimal(){
            var nombre by remember { mutableStateOf("Nombre") }
            var edad by remember { mutableStateOf("Edad") }
            var peso by remember { mutableStateOf("Peso") }
            Column (modifier = Modifier.padding(horizontal = 20.dp, vertical = 26.dp)){

                Text("INGRESA UN NUEVO ANIMAL",
                    modifier = Modifier.padding(vertical = 25.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))

                Text("Nombre:")
                TextField(
                    value = nombre,
                    modifier = Modifier.padding(vertical = 15.dp),
                    onValueChange = {
                        nombre = it
                    }
                )
                Text("Edad:")
                TextField(
                    value = edad,
                    modifier = Modifier.padding(vertical = 15.dp),
                    onValueChange = {
                        edad = it
                    }
                )
                Text("Peso:")
                TextField(
                    value = peso,
                    modifier = Modifier.padding(vertical = 15.dp),
                    onValueChange = {
                        peso = it
                    }
                )
                Button(
                    modifier = Modifier.padding(vertical = 10.dp),
                    onClick = {
                        animales += Animal(nombre,edad,peso)
                        nombre = ""
                        edad = ""
                        peso = ""
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
                    Column {
                        ListaFinal(animales)
                        agregarAnimal()

                    }




                }
            }
        }
    }
}





@Composable
fun listRow(text : String){
    Row {
        Text(text)
    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheVetAppTheme {
        Greeting("Android")
    }
}
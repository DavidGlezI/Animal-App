package com.example.thevetapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.thevetapp.ui.theme.TheVetAppTheme
import androidx.compose.ui.unit.dp


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheVetAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Register(auth, this)
                }
            }
        }
        auth = Firebase.auth
        Log.d("MyApp", "onCreate called")


    }

    public override fun onStart(){
        super.onStart()

        // lugar idóneo para verificar validez de usuario
        val currentUser = auth.currentUser

        if (currentUser == null) {
            Log.d("MyApp", "Usuario no creado")
        } else {

        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(auth : FirebaseAuth, activity: Activity? = null){
    // add 2 properties
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 40.dp)
    ){
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Login to Les Animaux") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(
            onClick = {
                if(activity != null){
                    if(login.isNotEmpty() && password.isNotEmpty()){
                        auth.signInWithEmailAndPassword(login, password)
                            .addOnCompleteListener(activity){task ->
                                if(task.isSuccessful){
                                    Toast.makeText(
                                        activity,
                                        "LOGIN CORRECTO: BIENVENIDO: ${auth.currentUser?.uid}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(activity, MenuActivity::class.java)
                                    activity.startActivity(intent)


                                } else {
                                    Toast.makeText(
                                        activity,
                                        "LOGIN INCORRECTO: ${task.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                    }
                    else{
                        Toast.makeText(
                            activity,
                            "Los campos de correo electrónico y contraseña son obligatorios",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }


            }
        ){
            Text("Login to LES ANIMAUX")
        }

        Button(
            onClick = {
                // vamos a necesitar referencia a la actividad
                // motivo: hay una task que necesita una actividad para ser escuchada
                // null safety en kotlin -
                // 1. safe call
                // 2. utilizar if
                if(activity != null){
                    if(login.isNotEmpty() && password.isNotEmpty()){
                        auth.createUserWithEmailAndPassword(login, password)
                            .addOnCompleteListener(activity){task ->
                                // RECUERDEN!
                                // codigo asíncrono termina en un tiempo no determinado
                                // AQUÍ ponemos lo que queremos que pase al terminar
                                if(task.isSuccessful){
                                    Toast.makeText(activity, "SIGN UP CORRECTO ${auth.currentUser?.uid}", Toast.LENGTH_SHORT).show()

                                } else {
                                    Toast.makeText(activity, "ERROR: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                    else{
                        Toast.makeText(
                            activity,
                            "Los campos de correo electrónico y contraseña son obligatorios",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

            }
        ){
            Text("Sign Up")
        }


    }
}



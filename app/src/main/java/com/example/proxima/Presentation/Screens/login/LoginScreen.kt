package com.example.proxima.Presentation.Screens.login

import android.content.ContentValues
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proxima.MainActivity
import com.example.proxima.ui.theme.ProXimaTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun LoginScreen(navController: NavHostController){

    val auth by lazy {
        Firebase.auth
    }

    val focusManager = LocalFocusManager.current

    var email by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf{
        password.length >7
    }

    var isPasswordVisible by remember{
        mutableStateOf(false)
    }

    Column(
        modifier= Modifier
            .background(color = Color.LightGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text= "Welcome back....",
            fontFamily= FontFamily.SansSerif,
            fontWeight= FontWeight.Bold,
            fontStyle= FontStyle.Italic,
            fontSize=32.sp,
            modifier= Modifier.padding(top=16.dp)
        )


        Text(
            text= "... to the house of fried chicken",
            fontFamily= FontFamily.SansSerif,
            fontWeight= FontWeight.Bold,
            fontStyle= FontStyle.Italic,
            fontSize=20.sp,
            modifier= Modifier.padding(bottom=20.dp)
        )

        Card(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape= RoundedCornerShape(16.dp),
            border= BorderStroke(1.dp, Color.Black)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier= Modifier.padding(all=10.dp)
            ){
                OutlinedTextField(value = email,
                    onValueChange = { email=it},
                    label={ Text("Email Address") },
                    placeholder={ Text("abc@domain.com") },
                    singleLine=true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext={ focusManager.moveFocus(FocusDirection.Down)}
                    ),
                    isError=!isEmailValid,
                    trailingIcon={
                        if (email.isNotBlank()){
                            IconButton(onClick = { email="" }) {
                                Icon(imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clear Email")
                            }
                        }
                    }
                )

                OutlinedTextField(value = password,
                    onValueChange = { password=it},
                    label={ Text("Password") },
                    singleLine=true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone={ focusManager.clearFocus()}
                    ),
                    isError=!isPasswordValid,
                    trailingIcon = {
                        IconButton(onClick ={isPasswordVisible=!isPasswordVisible}){
                            Icon(imageVector=if(isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription="Toggle password visibility")
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )

                Button(onClick = {
                    auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                Log.d(ContentValues.TAG,"The user has signed in successfully")
                            }
                            else{
                                Log.d(ContentValues.TAG,"The user has failed signing in",it.exception)
                            }
                        }
                },
                    modifier= Modifier.fillMaxWidth(),
                    colors= ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    enabled=isEmailValid && isPasswordVisible) {
                    Text(
                        text="Log in",
                        fontWeight = FontWeight.Bold,
                        color= Color.Black,
                        fontSize=16.sp
                    )
                }

            }

        }
        Row(
            horizontalArrangement= Arrangement.End,
            modifier= Modifier.fillMaxWidth()
        ){
            TextButton(onClick = { /*TODO*/ }) {

                Text(
                    color= Color.Black,
                    fontStyle= FontStyle.Italic,
                    text="Forgotten password?",
                    modifier = Modifier.padding(end=8.dp)
                )
            }
        }
        Button(
            onClick = { },
            enabled=true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            colors= ButtonDefaults.buttonColors(backgroundColor= Color.White)
        ){
            Text(
                color= Color.Black,
                fontWeight= FontWeight.Bold,
                text="Register",
                fontSize=16.sp
            )
        }
    }
}


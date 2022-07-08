package com.example.proxima

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.proxima.ui.theme.ProXimaTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationActivity : ComponentActivity() {
    companion object{
        val TAG: String=MainActivity::class.java.simpleName
    }
    private val auth by lazy {
        Firebase.auth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProXimaTheme {
                RegistrationActivity(auth)
            }
        }
    }
}


@Composable
fun RegistrationActivity(auth: FirebaseAuth){
    val focusManager = LocalFocusManager.current
    val context= LocalContext.current
    lateinit var email:String
    lateinit var password:String
    lateinit var confirmPassword:String
    lateinit var name:String
    lateinit var instaID:String

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
            text= "Registratio",
            fontFamily= FontFamily.Companion.SansSerif,
            fontWeight= FontWeight.Bold,
            fontStyle= FontStyle.Italic,
            fontSize=32.sp,
            modifier=Modifier.padding(top=16.dp)
        )

        Card(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape= RoundedCornerShape(16.dp),
            border= BorderStroke(1.dp,Color.Black)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier= Modifier.padding(all=10.dp)
            ){
                OutlinedTextField(value = email,
                    onValueChange = { email=it},
                    label={Text("Email Address")},
                    placeholder={Text("abc@domain.com")},
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
                    label={Text("Password")},
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

                OutlinedTextField(value = confirmPassword,
                    onValueChange = { confirmPassword=it},
                    label={Text("Confirm Password")},
                    singleLine=true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone={ focusManager.clearFocus()}
                    ),
                    isError=(!isPasswordValid&&(password==confirmPassword)),
                    trailingIcon = {
                        IconButton(onClick ={isPasswordVisible=!isPasswordVisible}){
                            Icon(imageVector=if(isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription="Toggle password visibility")
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )

                OutlinedTextField(value = name,
                    onValueChange = { name=it},
                    label={Text("Full Name")},
                    singleLine=true,
                    keyboardActions = KeyboardActions(
                        onDone={ focusManager.clearFocus()}
                    )
                )
                OutlinedTextField(value = instaID,
                    onValueChange = { instaID=it},
                    label={Text("Instagram ID")},
                    singleLine=true,
                    keyboardActions = KeyboardActions(
                        onDone={ focusManager.clearFocus()}
                    )
                )



            }

        }
        Button(
            onClick = {
                var user=User()
                user.name=name
                user.password=password
                user.email=email
                user.instaID=instaID

                val intent = Intent(context, RegistrationActivity2::class.java)
                intent.putExtra("user",user)
                context.startActivity(intent)
            },
            enabled=true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            colors=ButtonDefaults.buttonColors(backgroundColor=Color.White)
        ){
            Text(
                color=Color.Black,
                fontWeight= FontWeight.Bold,
                text="Next",
                fontSize=16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ProXimaTheme {
        RegistrationActivity(Firebase.auth)
    }
}
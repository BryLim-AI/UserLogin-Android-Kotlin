package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.loginapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

// Challenge:
    // create 2 users using array.
    // if 3 invalid attempt is made, automatically exits app and show a toast message.

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var attempts = 0
    var isValidLogin = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // referring to the layout.
        //only with id can be accessed.
        binding.btnSubmit.setOnClickListener{
            ValidateUser(it)
        }
    }

    private fun ValidateUser(it: View) {
        val usernameInput = binding.editUN.text.toString()
        val passwordInput = binding.editPass.text.toString()

        // store username and password using array.
        var usersArr = arrayListOf<Pair<String,String>>()
        usersArr.add(Pair("bryanlim","bry123"))
        usersArr.add(Pair("mobdev","mobdev123"))
        usersArr.add(Pair("coders","coders123"))
        usersArr.add(Pair("admin","admin123"))

        // if the user attempts to login with an invalid credentials then,
        // it will automatically shuts the program and show a toast that the user.
        // reaches the limit of attempts.

            // if the attempts is less than 3.
            for ((username, password) in usersArr) {
                if (username == usernameInput && password == passwordInput) {

                    val message = getString(R.string.welcome_message, username)
                    Snackbar.make(
                        it,
                        // to replace the hard coded message.
                        //"Welcome $username, This is Android Programming ",
                        message,
                        Snackbar.LENGTH_SHORT
                    )
                        .setAction("Show Details...", { displayToast() })
                        .show()
                    isValidLogin = true
                    break
                } //if clause
                else{
                    isValidLogin = false
                }
            }//for clause

        if(isValidLogin == false){
            val timeStamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
            val invalidAttempt = TextView(this)

            var counter = 1
            invalidAttempt.text = "Invalid attempt number ${++attempts} at $timeStamp"
            findViewById<LinearLayout>(R.id.layout).addView(invalidAttempt)
        }
        if (attempts == 3) {
            Toast.makeText(this, "You have reached a maximum of three (3) invalid login attempts", Toast.LENGTH_SHORT).show()
            finish()
        }

//        //convert editable object first to string.
//        if (username.toString()

    //        val timeStamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
//            val invalidAttempt = TextView(this)
//            invalidAttempt.text = "Invalid attempt number ${attempts++} at $timeStamp"
//            findViewById<LinearLayout>(R.id.layout).addView(invalidAttempt)

//                .equals("bryanqlim@gmail.com", ignoreCase = true) &&
//            password.toString().equals("bryan123")) {
//            // Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
//            val message = getString(R.string.welcome_message,username)
//            Snackbar.make(
//                it,
//                // to replace the hard coded message.
//                //"Welcome $username, This is Android Programming ",
//                message,
//                Snackbar.LENGTH_SHORT
//            )
//            //to create a button inside a snackbar.
//                .setAction("Show Details...", { displayToast() })
//                .show()
//        } else {
//            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
//        } //else statement

    }// end of validate user func.

    private fun displayToast() {
        Toast.makeText(this,"Login Successful ${Calendar.getInstance().time}",
        Toast.LENGTH_SHORT).show()
    }

    fun handleInvalidLogin() {
        attempts++
        val errorText = TextView(this)
        errorText.text = "Invalid attempt number $attempts at ${System.currentTimeMillis()}"

        if (attempts >= 3) {
            Toast.makeText(this, "You have reached a maximum of three (3) invalid login attempts", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun addTextView(text:String){
        val txtView1 = TextView(this)
        txtView1.text = text
        txtView1.textSize = 16f
        txtView1.textAlignment = View.TEXT_ALIGNMENT_CENTER
        binding.layout.addView(txtView1)
    }
}
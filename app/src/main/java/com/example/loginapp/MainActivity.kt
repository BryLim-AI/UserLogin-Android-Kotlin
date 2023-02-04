package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.loginapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

// Challenge:
    // create 2 users using array.
    // if 3 invalid attempt is made, automatically exits app and show a toast message.

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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

        var attempts = 0
        while (attempts < 3) { // if the attempts is less than 3.
            var isValidLogin = false
            for ((username, password) in usersArr) {
                if (username == usernameInput && password == passwordInput) {
                    isValidLogin = true
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
                    break
                } //if clause
            }//for clause
            if (!isValidLogin) {
                attempts++
                Toast.makeText(this, "Invalid Login. Attempts left: ${3 - attempts}", Toast.LENGTH_SHORT).show()
            } else {
                break
            }
        }//while
        if (attempts == 3) {
            Toast.makeText(this, "You have exceeded the maximum login attempts. Exiting program.", Toast.LENGTH_SHORT).show()
            finish() //exits program.
        }


//        //convert editable object first to string.
//        if (username.toString()
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
}
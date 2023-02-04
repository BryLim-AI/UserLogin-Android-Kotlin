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
        val username = binding.editUN.text
        val password = binding.editPass.text

        //convert editable object first to string.
        if (username.toString()
                .equals("bryanqlim@gmail.com", ignoreCase = true) &&
            password.toString().equals("bryan123")) {
            // Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val message = getString(R.string.welcome_message,username)
            Snackbar.make(
                it,
                // to replace the hard coded message.
                //"Welcome $username, This is Android Programming ",
                message,
                Snackbar.LENGTH_SHORT
            )
            //to create a button inside a snackbar.
                .setAction("Show Details...", { displayToast() })
                .show()
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayToast() {
        Toast.makeText(this,"Login Successful ${Calendar.getInstance().time}",
        Toast.LENGTH_SHORT).show()

    }
}
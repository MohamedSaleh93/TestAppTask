package com.saleh.testapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.saleh.testapp.login.view.LoginActivity
import com.saleh.testapp.signup.view.SignupActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun signUp(view: View) {
        intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

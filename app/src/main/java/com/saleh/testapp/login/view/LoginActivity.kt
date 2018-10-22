package com.saleh.testapp.login.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.saleh.testapp.R
import com.saleh.testapp.event.view.EventActivity
import com.saleh.testapp.login.viewmodel.LoginViewModel
import com.saleh.testapp.model.UserModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var model: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        model = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        model.getUserModel().observe(this, Observer { t ->  isUserExist(t)})
    }

    private fun isUserExist(userModel: UserModel?) {
        progressView.visibility = View.GONE
        if (userModel != null) {
            val intent = Intent(this, EventActivity::class.java)
            intent.putExtra("userName", userModel.userName)
            intent.putExtra("password", userModel.password)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, getString(R.string.user_not_exist), Toast.LENGTH_LONG).show()
        }
    }

    fun loginOperation(view: View) {
        val userName = userNameET.text.toString()
        val password = passwordET.text.toString()
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.enter_all_fields), Toast.LENGTH_LONG).show()
        } else {
            progressView.visibility = View.VISIBLE
            model.loginUSer(userName, password)
        }
    }
}

package com.saleh.testapp.signup.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.saleh.testapp.R
import com.saleh.testapp.event.view.EventActivity
import com.saleh.testapp.model.UserModel
import com.saleh.testapp.signup.viewmodel.SignupViewModel
import com.vansuita.pickimage.bean.PickResult

import kotlinx.android.synthetic.main.activity_signup.*
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import java.io.ByteArrayOutputStream


class SignupActivity : AppCompatActivity(), IPickResult {

    private lateinit var model: SignupViewModel
    private var userImage: Bitmap? = null
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        model = ViewModelProviders.of(this).get(SignupViewModel::class.java)
        model.isUserSaved().observe(this, Observer<Boolean> {
            t -> checkIsUserSaved(t)
        })
    }

    private fun checkIsUserSaved(userSaved: Boolean?) {
        progressView.visibility = View.GONE
        if (userSaved != null && userSaved) {
            val intent = Intent(this, EventActivity::class.java)
            intent.putExtra("userName", userModel.userName)
            intent.putExtra("password", userModel.password)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, getString(R.string.some_thing_went_wrong), Toast.LENGTH_LONG).show()
        }
    }

    fun signUpOperation(view: View) {
        var userName = userNameET.text.toString()
        var email = emailET.text.toString()
        var password = passwordET.text.toString()
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, getString(R.string.enter_all_fields), Toast.LENGTH_LONG).show()
        } else if (!model.isEmailValid(email)) {
            Toast.makeText(this, getString(R.string.enter_valid_email), Toast.LENGTH_LONG).show()
        } else if (!model.isPasswordValid(password)) {
            Toast.makeText(this, getString(R.string.enter_valid_password), Toast.LENGTH_LONG).show()
        } else {
            userModel = UserModel(userName, email, password, convertBitmapToByteArray(userImage))
            progressView.visibility = View.VISIBLE
            model.signUpUser(userModel)
        }
    }

    fun takeUserProfilePicture(view: View) {
        PickImageDialog.build(PickSetup()).show(this)
    }

    override fun onPickResult(r: PickResult?) {
        if (r?.error == null) {
            userImage = r?.bitmap
            userProfilePicture.setImageBitmap(userImage)
        } else {
            Toast.makeText(this, r.error.message, Toast.LENGTH_LONG).show();
        }
    }

    private fun convertBitmapToByteArray(bitmap: Bitmap?): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        bitmap?.recycle()
        return byteArray
    }
}



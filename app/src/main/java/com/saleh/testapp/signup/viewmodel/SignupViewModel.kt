package com.saleh.testapp.signup.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.SaveCallback
import com.saleh.testapp.model.UserModel
import java.util.regex.Pattern


class SignupViewModel: ViewModel() {

    var userSaved = MutableLiveData<Boolean>()

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    fun signUpUser(userModel: UserModel) {
        saveNewUSer(userModel)
    }

    private fun saveNewUSer(userModel: UserModel) {
        val userObject = ParseObject("UserData")
        userObject.put("userName", userModel.userName)
        userObject.put("email", userModel.email)
        userObject.put("password", userModel.password)
        if (userModel.userImage?.size!! > 0) {
            val userImageFile = ParseFile(userModel.userName + ".PNG", userModel.userImage)
            userImageFile.saveInBackground(SaveCallback { e ->
                if (e == null) {
                    userObject.put("userImage", userImageFile)
                    userObject.saveInBackground({ e ->
                        if (e?.message != null) {
                            Log.e("TAG", e?.message)
                        }
                        userSaved.value = e == null
                    })
                } else {
                    Log.e("TAG", e.message)
                    userSaved.value = false
                }
            })
        } else {
            userObject.saveInBackground({ e ->
                if (e?.message != null) {
                    Log.e("TAG", e?.message)
                }
                userSaved.value = e == null
            })
        }
    }

    fun isUserSaved(): LiveData<Boolean> {
        return userSaved
    }
}
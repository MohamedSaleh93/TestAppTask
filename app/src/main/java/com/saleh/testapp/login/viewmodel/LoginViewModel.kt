package com.saleh.testapp.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.parse.ParseObject
import com.parse.ParseQuery
import com.saleh.testapp.model.UserModel

/**
 * Created by mohamedsaleh on 10/22/18.
 */
class LoginViewModel: ViewModel() {

    var userModel = MutableLiveData<UserModel>()

    fun getUserModel(): LiveData<UserModel> {
        return userModel
    }

    fun loginUSer(userName: String, password: String) {
        val query = ParseQuery.getQuery<ParseObject>("UserData")
        query.whereEqualTo("userName", userName)
        query.whereEqualTo("password", password)
        query.limit = 1
        query.findInBackground { objects, e ->
            if (e == null) {
                if (objects.size > 0) {
                    val file = objects[0].getParseFile("userImage")
                    userModel.value = UserModel(objects[0].getString("userName")!!, objects[0].getString("email")!!,
                            objects[0].getString("password")!!, file?.data)
                } else {
                    userModel.value = null
                }
            } else {
                userModel.value = null
            }
        }

    }
}
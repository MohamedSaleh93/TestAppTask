package com.saleh.testapp.model

import java.util.*

data class UserModel(var userName: String,var email: String,var password: String, var userImage: ByteArray?){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserModel

        if (userName != other.userName) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (!Arrays.equals(userImage, other.userImage)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + (userImage?.let { Arrays.hashCode(it) } ?: 0)
        return result
    }
}
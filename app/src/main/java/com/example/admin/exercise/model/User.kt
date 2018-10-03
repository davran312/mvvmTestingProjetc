package com.example.admin.exercise.model

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

class User(val finalName:String,var finalAddress:String){

     var id:Long? = null
     var name:String? = null
     var address:String? = null
     var createdAt:String? = null
     var updatedAt:String? = null


    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\''
    }


}
package com.example.proxima

import java.io.Serializable

class User (): Serializable {
    var email:String = ""
    var password:String = ""
    var name:String=""
    var instaID:String = ""
    var age:Int = 0
    var gender:String=""
    var bio:String=""
    var interests = mutableListOf<String>()
    init{

    }
}
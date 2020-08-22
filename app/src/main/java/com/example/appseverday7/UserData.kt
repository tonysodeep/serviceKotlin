package com.example.appseverday7

class UserData{
    var id : Int = -1
    var accountName : String = ""
    var userName : String = ""
    var accountPass : String = ""

    constructor(id : Int,userName: String,accountName : String,accountPass : String){
        this.id = id
        this.accountName = accountName
        this.userName = userName
        this.accountPass = accountPass
    }

}

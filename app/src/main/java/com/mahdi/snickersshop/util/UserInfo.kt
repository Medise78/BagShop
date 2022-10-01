package com.mahdi.snickersshop.util

object UserInfo
{
     var username:String? = null
     var token:String? = null

     fun refreshToken(username:String?,newToken:String?){
          this.username = username
          this.token = newToken
     }
}
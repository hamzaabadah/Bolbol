package com.example.bolbol.apiclasses

object ApiLinks {
    private const val BASE_URL = "http://bolbol.effect.ps/"
    var REGISTER_URL = BASE_URL + "Auth/aaqreg.php"
    var LOGIN_URL = BASE_URL + "Auth/login.php"

    var AllCategories = "$BASE_URL/api/v1/categories"
}

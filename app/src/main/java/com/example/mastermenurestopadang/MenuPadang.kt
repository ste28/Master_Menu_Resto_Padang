package com.example.mastermenurestopadang

data class MenuPadang(var id:String, var nama:String, var deskripsi:String, var harga:Int) {
    fun summary():String = "$nama - $deskripsi - $harga"
}
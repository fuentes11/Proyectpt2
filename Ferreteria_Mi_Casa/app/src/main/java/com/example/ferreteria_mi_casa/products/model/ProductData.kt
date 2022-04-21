package com.example.ferreteria_mi_casa.products.model

class ProductData {
    /**set Data*/
    var name :String? = null
    var price:String? = null
    var image:String? = null
    constructor(){}

    constructor(
        name:String?,
        price:String?,
        image:String?
    ){
        this.name = name
        this.price = price
        this.image = image
    }
}
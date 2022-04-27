package com.example.ferreteria_mi_casa.products.model

class ProductData {
    /**set Data*/
    var key: String?= null
    var name :String? = null
    var price:String? = null
    var image:String? = null
    constructor(){}

    constructor(
        key: String?,
        name:String?,
        price:String?,
        image:String?
    ){
        this.name = name
        this.price = price
        this.image = image
    }
}
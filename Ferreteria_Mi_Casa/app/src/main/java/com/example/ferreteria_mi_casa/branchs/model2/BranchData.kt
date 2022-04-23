package com.example.ferreteria_mi_casa.branchs.model2

class BranchData {
    var name :String? = null
    var info:String? = null
    var image:String? = null
    constructor(){}

    constructor(
        name:String?,
        info:String?,
        image:String?
    ){
        this.name = name
        this.info = info
        this.image = image
    }
}
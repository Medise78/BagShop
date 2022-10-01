package com.mahdi.snickersshop.data.model

data class AdsResponse(
    val success:Boolean,
    val ads:List<Ads>
)

data class Ads(
    val adsId:String,
    val imageURL:String,
    val productId:String
)

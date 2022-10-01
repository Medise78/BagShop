package com.mahdi.snickersshop.util

import com.mahdi.snickersshop.R
import com.mahdi.snickersshop.data.model.Product

const val KEY_PRODUCT_ARG = "productId"
const val KEY_CATEGORY_ARG = "categoryName"
const val BASE_URL = "https://dunijet.ir/Projects/DuniBazaar/"
const val VALUE_SUCCESS = "success"
const val PAYMENT_SUCCESS = 1
const val PAYMENT_PENDING = 0
const val NO_PAYMENT = -2

val EMPTY_PRODUCT = Product("", "", "", "", "", "", "", "", "", "")

val CATEGORY = listOf(
    Pair("Backpack", R.drawable.back_pack),
    Pair("Handbag", R.drawable.hand_bag),
    Pair("Shopping", R.drawable.shopping_bag),
    Pair("Tote", R.drawable.tote_bag),
    Pair("Satchel", R.drawable.satchel_bag),
    Pair("Clutch", R.drawable.clutch_bag),
    Pair("Wallet", R.drawable.wallet_bag),
    Pair("Sling", R.drawable.sling_bag),
    Pair("Bucket", R.drawable.bucket_bag),
    Pair("Quilted", R.drawable.quilted_bag)
)

val TAGS = listOf(
    "Newest",
    "Best Sellers",
    "Most Visited",
    "Highest Quality"
)
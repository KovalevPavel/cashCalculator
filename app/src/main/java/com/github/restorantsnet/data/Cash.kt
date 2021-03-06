package com.github.restorantsnet.data

import com.github.restorantsnet.supertypes.CountCashMethod
import com.github.restorantsnet.supertypes.Currency
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cash(
    //ценность одной купюры
    val value: String,
    //количество купюр
    var cashAmount: String = "",
    //валюта
    val currency: Currency,
    //способ подсчета купюр
    var countMethod: CountCashMethod
)
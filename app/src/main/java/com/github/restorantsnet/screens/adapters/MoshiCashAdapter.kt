package com.github.restorantsnet.screens.adapters

import com.github.restorantsnet.data.Cash
import com.github.restorantsnet.data.countmethods.CountCash
import com.github.restorantsnet.data.countmethods.CountEmpty
import com.github.restorantsnet.supertypes.Currency
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

class MoshiCashAdapter {
    @FromJson
    fun convertToCash(cashToParse: CashToParse): Cash {
        val value = cashToParse.value
        val currency = findCurrency(cashToParse.currency) ?: Currency.GENERAL
        val countMethod =
            if (currency != Currency.GENERAL) CountCash() else CountEmpty()
        return Cash(
            value = value,
            currency = currency,
            countMethod = countMethod
        )
    }

    private fun findCurrency(sign: String) = Currency.values().find {
        it.sign == sign
    }

    @ToJson
    fun convertToJson(inputCash: Cash): String {
        return ""
    }

    @JsonClass(generateAdapter = true)
    data class CashToParse(
        val value: String,
        val currency: String
    )
}
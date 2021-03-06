package com.github.restorantsnet.supertypes

import android.icu.math.BigDecimal

interface CountCashMethod {
    fun countCash(cashCount: String, cashValue: Double = 0.toDouble()): Double
}
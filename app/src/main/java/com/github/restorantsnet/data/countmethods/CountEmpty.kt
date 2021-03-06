package com.github.restorantsnet.data.countmethods

import android.util.Log
import com.github.restorantsnet.supertypes.CountCashMethod

//класс, возвращающий размен в начале дня
class CountEmpty : CountCashMethod {
    override fun countCash(cashCount: String, cashValue: Double): Double {
        Log.d("ZAWARUDO", "Method: $cashCount")
        return cashCount.toDoubleOrNull() ?: 0.toDouble()
    }
}
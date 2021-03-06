package com.github.restorantsnet.data.countmethods

import com.github.restorantsnet.supertypes.CountCashMethod

//класс, высчитывающий сумму в определенных купюрах (монетах)
class CountCash : CountCashMethod {
    override fun countCash(cashCount: String, cashValue: Double): Double {
        return cashCount.toDoubleOrNull()?.let {
         it.toInt() * cashValue
        }?:0.toDouble()
    }
}
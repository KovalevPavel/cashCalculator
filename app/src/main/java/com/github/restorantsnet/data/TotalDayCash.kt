package com.github.restorantsnet.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class TotalDayCash(
    //размен
    val startDayCash: Double,
    //сумма на конец дня
    val endDayCash: BigDecimal
): Parcelable {
    fun getProfit() = "${endDayCash - startDayCash.toBigDecimal()}"
}

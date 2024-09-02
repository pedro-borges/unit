package org.pcb.unit.amount.finance

import java.math.BigDecimal
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency

object CurrencyConverter
{
    private val conversions = HashMap<Unit<Currency>, BigDecimal>()

    operator fun set(pair: Unit<Currency>, amount: BigDecimal)
    {
        conversions[pair] = amount
    }

    operator fun get(currency: Unit<Currency>) =
        conversions[currency] ?: throw IllegalArgumentException("No conversion for $currency")
}
package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.toBigDecimal

open class CurrencyAmount<C : Unit<Currency>>(
        amount: Number,
        unit: C)
    : Amount<Currency>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           domain = Currency::class,
                           units = mapOf(unit to 1)))
{
    val currency = unit

    $operators
}

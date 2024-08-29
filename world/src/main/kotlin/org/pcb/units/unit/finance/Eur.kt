package org.pcb.units.unit.finance

import org.pcb.units.Unit
import org.pcb.units.domain.Currency
import org.pcb.units.toBigBigDecimal

object Eur : Unit<Currency>(Currency::class)
{
    override val symbol = "EUR"
    override val cannon = 1.toBigBigDecimal()
}
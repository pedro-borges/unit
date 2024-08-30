package org.pcb.units.amount.length

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.length.Yard.YARD
import org.pcb.units.domain.Length
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Yard(amount: Number)
    : LengthAmount<YARD>(amount.toBigBigDecimal(), YARD)
{
    object YARD : Unit<Length>(Length::class)
    {
        override val symbol = "yd"
        override val scale = 0.9144.toBigBigDecimal()
    }
}
package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.Yard.YARD
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

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
package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.Thou.THOU
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Thou(amount: Number)
    : LengthAmount<THOU>(amount.toBigBigDecimal(), THOU)
{
    object THOU : Unit<Length>(Length::class)
    {
        override val symbol = "th"
        override val scale = 0.0000254.toBigBigDecimal()
    }
}
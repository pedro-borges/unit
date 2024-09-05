package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Yard(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = YARD)
{
    object YARD : Unit<Length>(Length::class)
    {
        override val symbol = "yd"
        override val scale = 0.9144.toBigBigDecimal()
    }
}
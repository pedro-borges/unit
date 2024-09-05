package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Thou(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = THOU)
{
    object THOU : Unit<Length>(Length::class)
    {
        override val symbol = "th"
        override val scale = 0.0000254.toBigBigDecimal()
    }
}
package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Foot(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = FOOT)
{
    object FOOT : Unit<Length>(Length::class)
    {
        override val symbol = "ft"
        override val scale = 0.3048.toBigBigDecimal()
    }
}
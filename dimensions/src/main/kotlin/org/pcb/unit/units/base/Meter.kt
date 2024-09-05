package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Meter(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount.toBigBigDecimal(),
    unit = METER)
{
    object METER : Unit<Length>(Length::class)
    {
        override val symbol = "m"
        override val scale = 1.toBigBigDecimal()
    }
}
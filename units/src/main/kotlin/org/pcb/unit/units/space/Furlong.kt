package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Furlong(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = FURLONG)
{
    object FURLONG : Unit<Length>(Length::class)
    {
        override val symbol = "fur"
        override val scale = 201.168.toBigBigDecimal()
    }
}
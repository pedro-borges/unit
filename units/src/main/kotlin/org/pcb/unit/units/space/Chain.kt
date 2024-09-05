package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class Chain(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = CHAIN)
{
    object CHAIN : Unit<Length>(Length::class)
    {
        override val symbol = "ch"
        override val scale = 20.1168.toBigBigDecimal()
    }
}
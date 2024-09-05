package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class League(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = LEAGUE)
{
    object LEAGUE : Unit<Length>(Length::class)
    {
        override val symbol = "lea"
        override val scale = 4828.032.toBigBigDecimal()
    }
}
package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.League.LEAGUE
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class League(amount: Number)
    : LengthAmount<LEAGUE>(amount.toBigBigDecimal(), LEAGUE)
{
    object LEAGUE : Unit<Length>(Length::class)
    {
        override val symbol = "lea"
        override val scale = 4828.032.toBigBigDecimal()
    }
}
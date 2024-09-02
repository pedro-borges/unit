package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.League.LEAGUE
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

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
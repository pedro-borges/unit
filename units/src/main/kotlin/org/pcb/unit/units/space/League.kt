package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.League.LEAGUE

@Suppress("unused")
class League(amount: Number)
    : LengthAmount<LEAGUE>(amount.toBigBigDecimal(), LEAGUE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object LEAGUE : Unit<Length>(Length::class)
    {
        override val symbol = "lea"
        override val scale = 4828.032.toBigBigDecimal()
    }
}
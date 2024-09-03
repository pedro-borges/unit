package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Foot.FOOT

@Suppress("unused")
class Foot(amount: Number)
    : LengthAmount<FOOT>(amount.toBigBigDecimal(), FOOT)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object FOOT : Unit<Length>(Length::class)
    {
        override val symbol = "ft"
        override val scale = 0.3048.toBigBigDecimal()
    }
}
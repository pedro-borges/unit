package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Foot(amount: Number)
    : LengthAmount<FOOT>(amount.toBigBigDecimal(), FOOT)
{
    object FOOT : Unit<Length>(Length::class)
    {
        override val symbol = "ft"
        override val scale = 0.3048.toBigBigDecimal()
    }
}
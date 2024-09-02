package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.Mile.MILE
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Mile(amount: Number)
    : LengthAmount<MILE>(amount.toBigBigDecimal(), MILE)
{
    object MILE : Unit<Length>(Length::class)
    {
        override val symbol = "mi"
        override val scale = 1609.344.toBigBigDecimal()
    }
}
package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.Mile.MILE
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

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
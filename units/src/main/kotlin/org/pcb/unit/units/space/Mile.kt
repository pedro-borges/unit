package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Mile.MILE

@Suppress("unused")
class Mile(amount: Number)
    : LengthAmount<MILE>(amount.toBigBigDecimal(), MILE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object MILE : Unit<Length>(Length::class)
    {
        override val symbol = "mi"
        override val scale = 1609.344.toBigBigDecimal()
    }
}
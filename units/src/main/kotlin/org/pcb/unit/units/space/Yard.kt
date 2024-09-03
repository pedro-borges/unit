package org.pcb.unit.units.space

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Yard.YARD

@Suppress("unused")
class Yard(amount: Number)
    : LengthAmount<YARD>(amount.toBigBigDecimal(), YARD)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object YARD : Unit<Length>(Length::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "yd"
        override val scale = 0.9144.toBigBigDecimal()
    }
}
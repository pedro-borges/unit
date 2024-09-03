package org.pcb.unit.units.space

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Thou.THOU

@Suppress("unused")
class Thou(amount: Number)
    : LengthAmount<THOU>(amount.toBigBigDecimal(), THOU)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object THOU : Unit<Length>(Length::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "th"
        override val scale = 0.0000254.toBigBigDecimal()
    }
}
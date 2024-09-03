package org.pcb.unit.units.space

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Furlong.FURLONG

@Suppress("unused")
class Furlong(amount: Number)
    : LengthAmount<FURLONG>(amount.toBigBigDecimal(), FURLONG)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object FURLONG : Unit<Length>(Length::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "fur"
        override val scale = 201.168.toBigBigDecimal()
    }
}
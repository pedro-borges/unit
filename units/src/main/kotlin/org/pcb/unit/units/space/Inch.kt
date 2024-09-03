package org.pcb.unit.units.space

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Inch.INCH

@Suppress("unused")
class Inch(amount: Number)
    : LengthAmount<INCH>(amount.toBigBigDecimal(), INCH)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object INCH : Unit<Length>(Length::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "in"
        override val scale = 0.0254.toBigBigDecimal()
    }
}
package org.pcb.units.amount.mass

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.units.amount.mass.Pound.POUND

@Suppress("unused")
class Pound(amount: Number)
    : MassAmount<POUND>(amount.toBigBigDecimal(), POUND)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object POUND : Unit<Mass>(Mass::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "lb"
        override val scale = 0.45359237.toBigBigDecimal()
    }
}
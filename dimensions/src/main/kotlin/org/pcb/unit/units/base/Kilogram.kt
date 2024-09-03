package org.pcb.unit.units.base

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM

@Suppress("unused")
class Kilogram(amount: Number)
    : MassAmount<KILOGRAM>(amount.toBigBigDecimal(), KILOGRAM)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object KILOGRAM : Unit<Mass>(Mass::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "Kg"
        override val scale = 1.toBigBigDecimal()
    }
}
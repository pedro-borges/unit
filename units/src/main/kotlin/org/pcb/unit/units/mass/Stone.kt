package org.pcb.unit.units.mass

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.mass.Stone.STONE

@Suppress("unused")
class Stone(amount: Number)
    : MassAmount<STONE>(amount.toBigBigDecimal(), STONE)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object STONE : Unit<Mass>(Mass::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "st"
        override val scale = 6.35029318.toBigBigDecimal()
    }
}
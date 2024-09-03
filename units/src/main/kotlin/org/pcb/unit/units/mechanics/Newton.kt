package org.pcb.unit.units.mechanics

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Force
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

@Suppress("unused")
class Newton(amount: Number)
    : ForceAmount<KILOGRAM, METER, SECOND>(amount = amount,
                                           major = KILOGRAM * METER,
                                           minor = SECOND * SECOND)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object NEWTON : Unit<Force>(Force::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "N"
        override val scale = 1.toBigBigDecimal()
    }
}
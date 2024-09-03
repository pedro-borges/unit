package org.pcb.unit.units.mechanics

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Power
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

@Suppress("unused")
class Watt(amount: Number)
    : PowerAmount<KILOGRAM, METER, SECOND>(amount, KILOGRAM, METER, METER, SECOND, SECOND, SECOND)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object WATT : Unit<Power>(Power::class)
    {
        override val symbol = "W"
        override val scale = 1.toBigBigDecimal()
    }
}
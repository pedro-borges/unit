package org.pcb.unit.amount.mechanics

import org.pcb.unit.Unit
import org.pcb.unit.amount.PowerAmount
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.dimension.Power
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Watt(amount: Number)
    : PowerAmount<KILOGRAM, METER, SECOND>(amount, KILOGRAM, METER, METER, SECOND, SECOND, SECOND)
{
    object WATT : Unit<Power>(Power::class)
    {
        override val symbol = "W"
        override val scale = 1.toBigBigDecimal()
    }
}
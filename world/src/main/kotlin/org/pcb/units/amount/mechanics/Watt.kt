package org.pcb.units.amount.mechanics

import org.pcb.units.Unit
import org.pcb.units.amount.PowerAmount
import org.pcb.units.amount.base.Kilogram.KILOGRAM
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.dimension.Power
import org.pcb.units.toBigBigDecimal

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
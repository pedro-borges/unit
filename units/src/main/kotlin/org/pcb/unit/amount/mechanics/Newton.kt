package org.pcb.unit.amount.mechanics

import org.pcb.unit.Unit
import org.pcb.unit.amount.ForceAmount
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.dimension.Force
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Newton(amount: Number)
    : ForceAmount<KILOGRAM, METER, SECOND>(amount, KILOGRAM, METER, SECOND, SECOND)
{
    object NEWTON : Unit<Force>(Force::class)
    {
        override val symbol = "N"
        override val scale = 1.toBigBigDecimal()
    }
}
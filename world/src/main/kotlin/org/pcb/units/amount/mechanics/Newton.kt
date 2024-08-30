package org.pcb.units.amount.mechanics

import org.pcb.units.Unit
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.domain.Force
import org.pcb.units.toBigBigDecimal

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
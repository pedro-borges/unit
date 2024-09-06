package org.pcb.unit.units.mechanics

import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ForceAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Newton(amount: Number)
    : ForceAmount<Unit<Mass>, Unit<Length>, Unit<Time>>(
    amount = amount,
    unit = U2(KILOGRAM, METER) to SECOND)
{
    object NEWTON : Unit<Force>(Force::class)
    {
        override val symbol = "N"
        override val scale = 1.toBigBigDecimal()
    }
}
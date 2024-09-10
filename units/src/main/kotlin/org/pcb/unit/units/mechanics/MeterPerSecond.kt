package org.pcb.unit.units.mechanics

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Speed
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class MeterPerSecond(amount: Number)
    : SpeedAmount<Unit<Distance>, Unit<Time>>(
    amount = amount,
    unit = METER to SECOND)
{
    object MPS : Unit<Speed>(Speed::class)
    {
        override val symbol = "Mps"
        override val scale = 1.toBigBigDecimal()
    }
}
package org.pcb.unit.amounts.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Speed
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.SpeedAmount

class MeterPerSecond(amount: Number,
                     magnitude: Magnitude = UNI)
    : SpeedAmount<Unit<Distance>, Unit<Time>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = METER to SECOND)
{
    open class MpsUnit(magnitude: Magnitude)
        : Unit<Speed>(Speed::class, "Mps", 1.toBigDecimal(), magnitude)

    object MPS : MpsUnit(UNI)
}

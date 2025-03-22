package org.pcb.unit.amounts.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.SpeedAmount
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Speed
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

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

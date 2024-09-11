package org.pcb.unit.units.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Speed
import org.pcb.unit.dimension.Time
import org.pcb.unit.units.SpeedAmount
import org.pcb.unit.units.base.Meter.MeterUnit
import org.pcb.unit.units.base.Second.SECOND

class MeterPerSecond(amount: Number,
                     magnitude: Magnitude = UNI)
    : SpeedAmount<Unit<Distance>, Unit<Time>>(
    amount = amount,
    unit = MeterUnit(magnitude) to SECOND)
{
    open class MpsUnit(magnitude: Magnitude)
        : Unit<Speed>(Speed::class, magnitude.symbol + "Mps", magnitude.scale)

    object MPS : MpsUnit(UNI)
}
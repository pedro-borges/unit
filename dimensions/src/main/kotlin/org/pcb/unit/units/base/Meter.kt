package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Meter(amount: Number,
            magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigBigDecimal(),
    unit = MeterUnit(magnitude))
{
    open class MeterUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "m", magnitude, 1.toBigBigDecimal())

    object METER : MeterUnit(UNI)
}


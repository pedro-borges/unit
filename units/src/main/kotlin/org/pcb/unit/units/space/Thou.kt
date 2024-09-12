package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.DistanceAmount

class Thou(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = THOU)
{
    open class ThouUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "th", 0.0000254.toBigDecimal(), magnitude)

    object THOU : ThouUnit(UNI)
}

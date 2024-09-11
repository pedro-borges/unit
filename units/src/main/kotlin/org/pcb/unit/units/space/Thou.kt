package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Thou(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = ThouUnit(magnitude))
{
    open class ThouUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "th", magnitude, 0.0000254.toBigBigDecimal())

    object THOU : ThouUnit(UNI)
}
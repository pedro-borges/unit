package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Foot(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount * magnitude.scale,
    unit = FOOT)
{
    open class FootUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "ft", magnitude, 0.3048.toBigBigDecimal())

    object FOOT : FootUnit(UNI)
}
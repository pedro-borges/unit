package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Mile(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount * magnitude.scale,
    unit = MILE)
{
    open class MileUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "mi", magnitude, 1609.344.toBigBigDecimal())

    object MILE : MileUnit(UNI)
}
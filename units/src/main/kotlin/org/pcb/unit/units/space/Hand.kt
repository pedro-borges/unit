package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Hand(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount * magnitude.scale,
    unit = HAND)
{
    open class HandUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "hh", magnitude, 0.1016.toBigBigDecimal())

    object HAND : HandUnit(UNI)
}
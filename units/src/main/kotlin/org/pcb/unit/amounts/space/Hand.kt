package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.DistanceAmount

class Hand(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = HAND)
{
    open class HandUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "hh", 0.1016.toBigDecimal(), magnitude)

    object HAND : HandUnit(UNI)
}

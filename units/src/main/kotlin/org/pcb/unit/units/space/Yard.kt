package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.DistanceAmount

class Yard(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = YARD)
{
    open class YardUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "yd", 0.9144.toBigDecimal(), magnitude)

    object YARD : YardUnit(UNI)
}

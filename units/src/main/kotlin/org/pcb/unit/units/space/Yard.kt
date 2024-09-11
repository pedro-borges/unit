package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Yard(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = YardUnit(magnitude))
{
    open class YardUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, magnitude.symbol + "yd", magnitude.scale * 0.9144.toBigBigDecimal())

    object YARD : YardUnit(UNI)
}
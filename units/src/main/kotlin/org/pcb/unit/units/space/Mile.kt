package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.DistanceAmount

class Mile(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = MILE)
{
    open class MileUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "mi", 1609.344.toBigDecimal(), magnitude)

    object MILE : MileUnit(UNI)
}

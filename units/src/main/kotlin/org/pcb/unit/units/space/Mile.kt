package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Mile(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = MileUnit(magnitude))
{
    open class MileUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, magnitude.symbol + "mi", magnitude.scale * 1609.344.toBigBigDecimal())

    object MILE : Unit<Distance>(Distance::class, "mi", 1609.344.toBigBigDecimal())
}
package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.DistanceAmount

class Inch(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = INCH)
{
    open class InchUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "in", 0.0254.toBigDecimal(), magnitude)

    object INCH : InchUnit(UNI)
}

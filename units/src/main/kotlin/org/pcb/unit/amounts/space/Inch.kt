package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal

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

package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Inch(amount: Number,
           magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount,
    unit = InchUnit(magnitude))
{
    open class InchUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, magnitude.symbol + "in", magnitude.scale * 0.0254.toBigBigDecimal())

    object INCH : InchUnit(UNI)
}
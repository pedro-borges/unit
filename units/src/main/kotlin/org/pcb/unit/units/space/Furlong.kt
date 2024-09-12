package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Furlong(amount: Number,
              magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount * magnitude.scale,
    unit = FURLONG)
{
    open class FurlongUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "fur", magnitude, 201.168.toBigBigDecimal())

    object FURLONG : FurlongUnit(UNI)
}

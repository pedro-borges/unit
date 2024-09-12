package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.DistanceAmount

class Furlong(amount: Number,
              magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = FURLONG)
{
    open class FurlongUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "fur", 201.168.toBigDecimal(), magnitude)

    object FURLONG : FurlongUnit(UNI)
}

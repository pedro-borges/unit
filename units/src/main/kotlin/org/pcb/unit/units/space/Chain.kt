package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.DistanceAmount

class Chain(amount: Number,
            magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount * magnitude.scale,
    unit = CHAIN)
{
    open class ChainUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "ch", magnitude, 20.1168.toBigBigDecimal())

    object CHAIN : ChainUnit(UNI)
}
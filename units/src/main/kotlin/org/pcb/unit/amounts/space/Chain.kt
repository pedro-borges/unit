package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal

class Chain(amount: Number,
            magnitude: Magnitude = UNI)
    : DistanceAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = CHAIN)
{
    open class ChainUnit(magnitude: Magnitude)
        : Unit<Distance>(Distance::class, "ch", 20.1168.toBigDecimal(), magnitude)

    object CHAIN : ChainUnit(UNI)
}
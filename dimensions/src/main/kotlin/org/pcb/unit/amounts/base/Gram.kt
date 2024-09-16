package org.pcb.unit.amounts.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

class Gram(amount: Number,
           magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = GRAM)
{
    open class GramUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "g", 0.001.toBigDecimal(), magnitude)

    object GRAM : GramUnit(UNI)
}

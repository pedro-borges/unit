package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Gram.GramUnit

class Gram(amount: Number,
           magnitude: Magnitude = UNI)
    : MassAmount<GramUnit>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = GRAM)
{
    open class GramUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "g", 0.001.toBigDecimal(), magnitude)

    object GRAM : GramUnit(UNI)
}

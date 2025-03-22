package org.pcb.unit.base

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.MassAmount
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.toBigDecimal

class Gram(amount: Number,
           magnitude: Magnitude = UNI)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigDecimal(),
    unit = GramUnit(magnitude))
{
    open class GramUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "g", 0.001.toBigDecimal(), magnitude)

    object GRAM : GramUnit(UNI)
    object KILOGRAM : GramUnit(KILO)
}

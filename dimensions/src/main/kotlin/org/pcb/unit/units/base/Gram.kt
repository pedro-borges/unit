package org.pcb.unit.units.base

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Gram.GramUnit

class Gram(amount: Number,
           magnitude: Magnitude = UNI)
    : MassAmount<GramUnit>(
    amount = amount.toBigBigDecimal(),
    unit = GramUnit(magnitude))
{
    open class GramUnit(magnitude: Magnitude)
        : Unit<Mass>(Mass::class, "g", magnitude, 0.001.toBigBigDecimal())

    object GRAM : GramUnit(UNI)
}


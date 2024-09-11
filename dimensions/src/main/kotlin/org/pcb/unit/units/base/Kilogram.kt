package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Kilogram(amount: Number)
    : MassAmount<Unit<Mass>>(
    amount = amount.toBigBigDecimal(),
    unit = KILOGRAM)
{
    object KILOGRAM : Unit<Mass>(Mass::class, "Kg", 1.toBigBigDecimal())
}
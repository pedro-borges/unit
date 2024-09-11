package org.pcb.unit.units.base

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount
import org.pcb.unit.units.base.Gram.GRAM

class Gram(amount: Number)
    : MassAmount<GRAM>(
    amount = amount.toBigBigDecimal(),
    unit = GRAM)
{
    object GRAM : Unit<Mass>(Mass::class, "g", 0.001.toBigBigDecimal())
}
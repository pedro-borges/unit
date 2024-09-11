package org.pcb.unit.units.mass

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Ounce(amount: Number)
    : MassAmount<Unit<Mass>>(
    amount = amount,
    unit = OUNCE)
{
    object OUNCE : Unit<Mass>(Mass::class, "oz", 0.028349523125.toBigBigDecimal())
}
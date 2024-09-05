package org.pcb.unit.units.mass

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.MassAmount

class Stone(amount: Number)
    : MassAmount<Unit<Mass>>(
    amount = amount,
    unit = STONE)
{
    object STONE : Unit<Mass>(Mass::class)
    {
        override val symbol = "st"
        override val scale = 6.35029318.toBigBigDecimal()
    }
}
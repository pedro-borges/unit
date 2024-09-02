package org.pcb.unit.amount.mass

import org.pcb.unit.Unit
import org.pcb.unit.amount.MassAmount
import org.pcb.unit.amount.mass.Ounce.OUNCE
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Ounce(amount: Number)
    : MassAmount<OUNCE>(amount.toBigBigDecimal(), OUNCE)
{
    object OUNCE : Unit<Mass>(Mass::class)
    {
        override val symbol = "oz"
        override val scale = 28.349523125.toBigBigDecimal()
    }
}
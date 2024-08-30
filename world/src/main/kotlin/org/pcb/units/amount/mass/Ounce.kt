package org.pcb.units.amount.mass

import org.pcb.units.Unit
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.domain.Mass
import org.pcb.units.toBigBigDecimal

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
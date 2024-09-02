package org.pcb.units.amount.base

import org.pcb.units.Unit
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.base.Kilogram.KILOGRAM
import org.pcb.units.dimension.Mass
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Kilogram(amount: Number)
    : MassAmount<KILOGRAM>(amount.toBigBigDecimal(), KILOGRAM)
{
    object KILOGRAM : Unit<Mass>(Mass::class)
    {
        override val symbol = "Kg"
        override val scale = 1.toBigBigDecimal()
    }
}
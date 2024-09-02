package org.pcb.unit.amount.base

import org.pcb.unit.Unit
import org.pcb.unit.amount.MassAmount
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal

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
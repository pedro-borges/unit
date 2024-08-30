package org.pcb.units.amount.cannon

import org.pcb.units.Unit
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.domain.Mass
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
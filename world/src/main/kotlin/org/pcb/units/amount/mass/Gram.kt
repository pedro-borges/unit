package org.pcb.units.amount.mass

import org.pcb.units.Unit
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.mass.Gram.GRAM
import org.pcb.units.domain.Mass
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Gram(amount: Number)
    : MassAmount<GRAM>(amount.toBigBigDecimal(), GRAM)
{
    object GRAM : Unit<Mass>(Mass::class)
    {
        override val symbol = "g"
        override val scale = 1.toBigBigDecimal()
    }
}
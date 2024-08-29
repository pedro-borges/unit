package org.pcb.units.amount.mass

import org.pcb.units.Unit
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.domain.Mass
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Pound(amount: Number)
    : MassAmount<POUND>(amount.toBigBigDecimal(), POUND)
{
    object POUND : Unit<Mass>(Mass::class)
    {
        override val symbol = "lb"
        override val cannon = 453.59237.toBigBigDecimal()
    }
}
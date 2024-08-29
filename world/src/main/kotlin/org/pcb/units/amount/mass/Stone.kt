package org.pcb.units.amount.mass

import org.pcb.units.Unit
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.mass.Stone.STONE
import org.pcb.units.domain.Mass
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Stone(amount: Number)
    : MassAmount<STONE>(amount.toBigBigDecimal(), STONE)
{
    object STONE : Unit<Mass>(Mass::class)
    {
        override val symbol = "st"
        override val cannon = 6.35029318000.toBigBigDecimal()
    }
}
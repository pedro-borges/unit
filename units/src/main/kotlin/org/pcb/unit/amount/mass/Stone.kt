package org.pcb.unit.amount.mass

import org.pcb.unit.Unit
import org.pcb.unit.amount.MassAmount
import org.pcb.unit.amount.mass.Stone.STONE
import org.pcb.unit.dimension.Mass
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Stone(amount: Number)
    : MassAmount<STONE>(amount.toBigBigDecimal(), STONE)
{
    object STONE : Unit<Mass>(Mass::class)
    {
        override val symbol = "st"
        override val scale = 6.35029318000.toBigBigDecimal()
    }
}
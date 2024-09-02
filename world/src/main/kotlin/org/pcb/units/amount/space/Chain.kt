package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.Chain.CHAIN
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Chain(amount: Number)
    : LengthAmount<CHAIN>(amount.toBigBigDecimal(), CHAIN)
{
    object CHAIN : Unit<Length>(Length::class)
    {
        override val symbol = "ch"
        override val scale = 20.1168.toBigBigDecimal()
    }
}
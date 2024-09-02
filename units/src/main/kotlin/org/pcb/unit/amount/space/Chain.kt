package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.Chain.CHAIN
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

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
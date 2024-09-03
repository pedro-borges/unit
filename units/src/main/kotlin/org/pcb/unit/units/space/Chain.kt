package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.Chain.CHAIN

@Suppress("unused")
class Chain(amount: Number)
    : LengthAmount<CHAIN>(amount.toBigBigDecimal(), CHAIN)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object CHAIN : Unit<Length>(Length::class)
    {
        override val symbol = "ch"
        override val scale = 20.1168.toBigBigDecimal()
    }
}
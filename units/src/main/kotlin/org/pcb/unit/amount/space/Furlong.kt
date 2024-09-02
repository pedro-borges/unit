package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.Furlong.FURLONG
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Furlong(amount: Number)
    : LengthAmount<FURLONG>(amount.toBigBigDecimal(), FURLONG)
{
    object FURLONG : Unit<Length>(Length::class)
    {
        override val symbol = "fur"
        override val scale = 201.168.toBigBigDecimal()
    }
}
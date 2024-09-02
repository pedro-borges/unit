package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.Furlong.FURLONG
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

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
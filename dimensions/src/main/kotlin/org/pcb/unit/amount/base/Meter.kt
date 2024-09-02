package org.pcb.unit.amount.base

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Meter(amount: Number)
    : LengthAmount<METER>(amount.toBigBigDecimal(), METER)
{
    object METER : Unit<Length>(Length::class)
    {
        override val symbol = "m"
        override val scale = 1.toBigBigDecimal()
    }
}
package org.pcb.units.amount.cannon

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.domain.Length
import org.pcb.units.toBigBigDecimal

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
package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.LengthAmount
import org.pcb.unit.amount.space.DeciMeter.DECIMETER
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class DeciMeter(amount: Number)
    : LengthAmount<DECIMETER>(amount.toBigBigDecimal(), DECIMETER)
{
    object DECIMETER : Unit<Length>(Length::class)
    {
        override val symbol = "dm"
        override val scale = 0.01.toBigBigDecimal()
    }
}
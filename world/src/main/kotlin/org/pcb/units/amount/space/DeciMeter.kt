package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.LengthAmount
import org.pcb.units.amount.space.DeciMeter.DECIMETER
import org.pcb.units.dimension.Length
import org.pcb.units.toBigBigDecimal

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
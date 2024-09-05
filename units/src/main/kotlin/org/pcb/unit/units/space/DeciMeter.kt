package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount

class DeciMeter(amount: Number)
    : LengthAmount<Unit<Length>>(
    amount = amount,
    unit = DECIMETER)
{
    object DECIMETER : Unit<Length>(Length::class)
    {
        override val symbol = "dm"
        override val scale = 0.01.toBigBigDecimal()
    }
}
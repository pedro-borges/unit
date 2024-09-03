package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.LengthAmount
import org.pcb.unit.units.space.DeciMeter.DECIMETER

@Suppress("unused")
class DeciMeter(amount: Number)
    : LengthAmount<DECIMETER>(amount.toBigBigDecimal(), DECIMETER)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object DECIMETER : Unit<Length>(Length::class)
    {
        override val symbol = "dm"
        override val scale = 0.01.toBigBigDecimal()
    }
}
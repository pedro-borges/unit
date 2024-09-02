package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.VolumeAmount
import org.pcb.unit.amount.space.DeciMeter.DECIMETER
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigBigDecimal

@Suppress("unused")
class Liter(amount: Number)
    : VolumeAmount<DECIMETER>(amount.toBigBigDecimal(), DECIMETER, DECIMETER, DECIMETER)
{
    object LITER : Unit<Volume>(Volume::class)
    {
        override val symbol = "L"
        override val scale = 1.toBigBigDecimal()
    }
}
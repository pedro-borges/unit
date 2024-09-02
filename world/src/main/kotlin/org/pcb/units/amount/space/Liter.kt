package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.VolumeAmount
import org.pcb.units.amount.space.DeciMeter.DECIMETER
import org.pcb.units.dimension.Volume
import org.pcb.units.toBigBigDecimal

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
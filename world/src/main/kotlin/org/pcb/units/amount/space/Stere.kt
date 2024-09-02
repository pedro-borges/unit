package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.VolumeAmount
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.dimension.Volume
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Stere(amount: Number)
    : VolumeAmount<METER>(amount.toBigBigDecimal(), METER, METER, METER)
{
    object STERE : Unit<Volume>(Volume::class)
    {
        override val symbol = "st"
        override val scale = 1.toBigBigDecimal()
    }
}
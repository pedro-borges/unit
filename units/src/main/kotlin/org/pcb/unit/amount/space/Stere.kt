package org.pcb.unit.amount.space

import org.pcb.unit.Unit
import org.pcb.unit.amount.VolumeAmount
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigBigDecimal

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
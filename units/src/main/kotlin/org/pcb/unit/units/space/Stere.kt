package org.pcb.unit.units.space

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter.METER

@Suppress("unused")
class Stere(amount: Number)
    : VolumeAmount<METER>(amount = amount,
                          major = METER * METER * METER)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object STERE : Unit<Volume>(Volume::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override val symbol = "st"
        override val scale = 1.toBigBigDecimal()
    }
}
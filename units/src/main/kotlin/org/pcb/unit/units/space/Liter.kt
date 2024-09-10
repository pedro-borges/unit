package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.space.DeciMeter.DECIMETER

class Liter(amount: Number)
    : VolumeAmount<Unit<Distance>>(
    amount = amount,
    unit = DECIMETER)
{
    object LITER : Unit<Volume>(Volume::class)
    {
        override val symbol = "L"
        override val scale = 0.001.toBigBigDecimal()
    }
}
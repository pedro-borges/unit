package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter.METER

class Stere(amount: Number)
    : VolumeAmount<Unit<Distance>>(
    amount = amount,
    unit = METER)
{
    object STERE : Unit<Volume>(Volume::class, "st", 1.toBigBigDecimal())
}
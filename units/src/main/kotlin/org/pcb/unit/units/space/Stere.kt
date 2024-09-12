package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Volume
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter.METER

class Stere(amount: Number,
            magnitude: Magnitude = UNI)
    : VolumeAmount<Unit<Distance>>(
    amount = amount * magnitude.scale,
    unit = METER)
{
    open class StereUnit(magnitude: Magnitude)
        : Unit<Volume>(Volume::class, "st", magnitude, 1.toBigBigDecimal())

    object STERE : StereUnit(UNI)
}

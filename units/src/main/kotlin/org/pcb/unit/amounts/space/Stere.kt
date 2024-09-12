package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Volume
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.VolumeAmount

class Stere(amount: Number,
            magnitude: Magnitude = UNI)
    : VolumeAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = METER)
{
    open class StereUnit(magnitude: Magnitude)
        : Unit<Volume>(Volume::class, "st", 1.toBigDecimal(), magnitude)

    object STERE : StereUnit(UNI)
}

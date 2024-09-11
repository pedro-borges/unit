package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Volume
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter.MeterUnit

class Stere(amount: Number,
            magnitude: Magnitude = UNI)
    : VolumeAmount<Unit<Distance>>(
    amount = amount,
    unit = MeterUnit(magnitude))
{
    open class StereUnit(magnitude: Magnitude)
        : Unit<Volume>(Volume::class, magnitude.symbol + "st", magnitude.scale)

    object STERE : StereUnit(UNI)
}
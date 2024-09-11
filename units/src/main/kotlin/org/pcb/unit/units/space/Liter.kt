package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.deci
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter.MeterUnit

class Liter(amount: Number,
            magnitude: Magnitude = UNI)
    : VolumeAmount<Unit<Distance>>(
    amount = amount,
    unit = deci(MeterUnit(magnitude)))
{
    open class LiterUnit(magnitude: Magnitude)
        : Unit<Volume>(Volume::class, magnitude.symbol + "L", magnitude.scale * 0.001.toBigBigDecimal())

    object LITER : LiterUnit(UNI)
}
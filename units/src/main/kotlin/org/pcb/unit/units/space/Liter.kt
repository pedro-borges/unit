package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.deci
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.base.Meter.METER

class Liter(amount: Number,
            magnitude: Magnitude = UNI)
    : VolumeAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = deci(METER))
{
    open class LiterUnit(magnitude: Magnitude)
        : Unit<Volume>(Volume::class, "L", 0.001.toBigDecimal(), magnitude)

    object LITER : LiterUnit(UNI)
}

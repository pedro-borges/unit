package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.VolumeAmount
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.deci
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Volume
import org.pcb.unit.toBigDecimal

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

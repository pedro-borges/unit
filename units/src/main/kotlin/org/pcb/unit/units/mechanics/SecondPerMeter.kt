package org.pcb.unit.units.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Pace
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.PaceAmount
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class SecondPerMeter(amount: Number,
                     magnitude: Magnitude = UNI)
    : PaceAmount<Unit<Time>, Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = SECOND to METER)
{
    open class SpmUnit(magnitude: Magnitude)
        : Unit<Pace>(Pace::class, "Spm", 1.toBigDecimal(), magnitude)

    object SPM : SpmUnit(UNI)
}

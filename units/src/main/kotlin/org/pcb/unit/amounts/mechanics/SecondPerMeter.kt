package org.pcb.unit.amounts.mechanics

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.PaceAmount
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Pace
import org.pcb.unit.dimensions.Time
import org.pcb.unit.toBigDecimal

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

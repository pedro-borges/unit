package org.pcb.unit.units.mechanics

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.Pace
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.PaceAmount
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class SecondPerMeter(amount: Number)
    : PaceAmount<Unit<Time>, Unit<Distance>>(
    amount = amount,
    unit = SECOND to METER)
{
    object SPM : Unit<Pace>(Pace::class, "Spm", 1.toBigBigDecimal())
}
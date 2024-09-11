package org.pcb.unit.units.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Area
import org.pcb.unit.dimension.Distance
import org.pcb.unit.units.AreaAmount
import org.pcb.unit.units.base.Meter.MeterUnit

class SquareMeter(amount: Number,
                  magnitude: Magnitude = UNI)
    : AreaAmount<Unit<Distance>>(
    amount = amount,
    unit = MeterUnit(magnitude))
{
    open class SqmUnit(magnitude: Magnitude)
        : Unit<Area>(Area::class, magnitude.symbol + "sqm", magnitude.scale)

    object SQM : SqmUnit(UNI)
}
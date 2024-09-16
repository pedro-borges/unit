package org.pcb.unit.amounts.space

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.Companion.UNI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.dimensions.Area
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.toBigDecimal

class SquareMeter(amount: Number,
                  magnitude: Magnitude = UNI)
    : AreaAmount<Unit<Distance>>(
    amount = amount.toBigDecimal().movePointRight(magnitude.power),
    unit = METER)
{
    open class SqmUnit(magnitude: Magnitude)
        : Unit<Area>(Area::class, "sqm", 1.toBigDecimal(), magnitude)

    object SQM : SqmUnit(UNI)
}

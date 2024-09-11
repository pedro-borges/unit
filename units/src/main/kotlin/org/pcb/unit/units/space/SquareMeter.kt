package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Area
import org.pcb.unit.dimension.Distance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.AreaAmount
import org.pcb.unit.units.base.Meter.METER

class SquareMeter(amount: Number)
    : AreaAmount<Unit<Distance>>(
    amount = amount,
    unit = METER)
{
    object SQUARE_METER : Unit<Area>(Area::class, "sqm", 1.toBigBigDecimal())
}
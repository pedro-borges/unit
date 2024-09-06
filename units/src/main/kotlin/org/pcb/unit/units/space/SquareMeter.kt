package org.pcb.unit.units.space

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Area
import org.pcb.unit.dimension.Length
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.AreaAmount
import org.pcb.unit.units.base.Meter.METER

class SquareMeter(amount: Number)
    : AreaAmount<Unit<Length>>(
    amount = amount,
    unit = METER)
{
    object SQUARE_METER : Unit<Area>(Area::class)
    {
        override val symbol = "sqm"
        override val scale = 1.toBigBigDecimal()
    }
}
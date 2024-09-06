package org.pcb.unit.units.mechanics

import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Power
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Watt(amount: Number)
    : PowerAmount<Unit<Mass>, Unit<Length>, Unit<Time>>(
    amount = amount,
    unit = Tuple(KILOGRAM, METER) / SECOND)
{
    object WATT : Unit<Power>(Power::class)
    {
        override val symbol = "W"
        override val scale = 1.toBigBigDecimal()
    }
}
package org.pcb.units.amount.mechanics

import org.pcb.units.Unit
import org.pcb.units.amount.PowerAmount
import org.pcb.units.amount.cannon.Kilogram.KILOGRAM
import org.pcb.units.amount.cannon.Meter.METER
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.domain.Power
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Watt(amount: Number)
    : PowerAmount<KILOGRAM, METER, SECOND>(amount, KILOGRAM, METER, METER, SECOND, SECOND, SECOND)
{
    object WATT : Unit<Power>(Power::class)
    {
        override val symbol = "W"
        override val scale = 1.toBigBigDecimal()
    }
}
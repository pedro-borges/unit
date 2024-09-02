package org.pcb.units.amount.mechanics

import org.pcb.units.Unit
import org.pcb.units.amount.EnergyAmount
import org.pcb.units.amount.base.Kilogram.KILOGRAM
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.dimension.Energy
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Joule(amount: Number)
    : EnergyAmount<KILOGRAM, METER,  SECOND>(amount, KILOGRAM, METER, METER, SECOND, SECOND)
{
    object JOULE : Unit<Energy>(Energy::class)
    {
        override val symbol = "J"
        override val scale = 1.toBigBigDecimal()
    }
}
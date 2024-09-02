package org.pcb.unit.amount.mechanics

import org.pcb.unit.Unit
import org.pcb.unit.amount.EnergyAmount
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.dimension.Energy
import org.pcb.unit.toBigBigDecimal

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
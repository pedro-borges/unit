package org.pcb.units.amount.space

import org.pcb.units.Unit
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.domain.Space
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Meter(amount: Number)
    : SpaceAmount<METER>(amount.toBigBigDecimal(), METER)
{
    object METER : Unit<Space>(Space::class)
    {
        override val symbol = "m"
        override val scale = 1.toBigBigDecimal()
    }
}
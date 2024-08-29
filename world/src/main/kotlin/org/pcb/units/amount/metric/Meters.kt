package org.pcb.units.amount.metric

import org.pcb.units.Unit
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.metric.Meters.METER
import org.pcb.units.domain.Space
import org.pcb.units.toBigBigDecimal

@Suppress("unused")
class Meters(amount: Number)
    : SpaceAmount<METER>(amount.toBigBigDecimal(), METER)
{
    object METER : Unit<Space>(Space::class)
    {
        override val symbol = "m"
        override val cannon = 1.toBigBigDecimal()
    }
}
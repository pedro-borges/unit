package org.pcb.units.amounts.domain

import org.pcb.units.domain.Space
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class SpaceAmount(amount: Number,
                       val space: Unit<Space>)
    : Amount<Space>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        unit = space,
                        domain = Space::class))
{
    operator fun div(that: TimeAmount) =
        SpeedAmount(amount = this.amount / that.amount,
                    space = space,
                    time = that.unit)
}
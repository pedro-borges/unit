package org.pcb.units.amounts.domain

import org.pcb.units.domain.Mass
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class MassAmount(amount: Number,
                      val mass: Unit<Mass>)
    : Amount<Mass>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       unit = mass,
                       domain = Mass::class))
{
    operator fun times(that: AccelerationAmount) =
        ForceAmount(amount = that.amount * that.amount,
                    space = that.space,
                    time1 = that.time1,
                    time2 = that.time2,
                    mass = mass)
}
package org.pcb.units.amounts.generic

import org.pcb.units.domain.Mass
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class MassAmount<M : Unit<Mass>>(amount: Number,
                      val mass: M)
    : Amount<Mass>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       unit = mass,
                       domain = Mass::class))
{
    operator fun times(that: SpeedAmount<*, *>) =
        MomentumAmount(amount = that.amount * that.amount,
                       mass = mass,
                       space = that.space,
                       time = that.time)

    operator fun times(that: AccelerationAmount<*, *, *>) =
        ForceAmount(amount = that.amount * that.amount,
                    mass = mass,
                    space = that.space,
                    time1 = that.time1,
                    time2 = that.time2)
}
package org.pcb.units.amounts.generic

import org.pcb.units.domain.Mass
import org.pcb.units.domain.Momentum
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class MomentumAmount(amount: Number,
                          val mass: Unit<Mass>,
                          val space: Unit<Space>,
                          val time: Unit<Time>)
    : Amount<Momentum>(amount = amount.toBigDecimal(),
                       unit = CompositeUnit(
                           units = mapOf(mass to 1,
                                         space to 1,
                                         time to 1),
                           domain = Momentum::class))
{
    operator fun div(that: MassAmount) =
        when
        {
            mass.cannon == that.unit.cannon ->
                SpeedAmount(amount = amount / that.amount,
                            space = space,
                            time = time)
            else                            ->
                SpeedAmount(amount = amount * mass.cannon / that.amount / that.unit.cannon,
                            space = space,
                            time = time)
        }

    operator fun div(that: SpeedAmount) =
        when
        {
            space.cannon / time.cannon == that.unit.cannon ->
                MassAmount(amount = amount / that.amount,
                           mass = mass)
            else                                           ->
                MassAmount(amount = amount * space.cannon / that.amount / that.unit.cannon,
                           mass = mass)
        }

    operator fun div(that: TimeAmount) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    space = space,
                    time1 = time,
                    time2 = that.unit)
}

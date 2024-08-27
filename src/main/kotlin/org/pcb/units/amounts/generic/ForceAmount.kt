package org.pcb.units.amounts.generic

import org.pcb.units.domain.Force
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit
import org.pcb.units.unit.mechanics.Kilogram.Companion.KILOGRAM
import org.pcb.units.unit.mechanics.Newton
import org.pcb.units.unit.metric.Meter.Companion.METER
import org.pcb.units.unit.metric.Second.Companion.SECOND

open class ForceAmount(amount: Number,
                       val mass: Unit<Mass>,
                       val space: Unit<Space>,
                       val time1: Unit<Time>,
                       val time2: Unit<Time>)
    : Amount<Force>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        units =
                        if (time1.cannon == time2.cannon)
                            mapOf(mass to 1,
                                  space to 1,
                                  time1 to -2)
                        else
                            mapOf(mass to 1,
                                  space to 1,
                                  time1 to -1,
                                  time2 to -1),
                        domain = Force::class))
{
    constructor(amount: Number, unit: Newton) : this(amount, KILOGRAM, METER, SECOND, SECOND)

    operator fun div(that: MassAmount) =
        when
        {
            mass.cannon == that.unit.cannon ->
                AccelerationAmount(amount = amount / that.amount,
                                   space = space,
                                   time1 = time1,
                                   time2 = time2)
            else                            ->
                AccelerationAmount(amount = amount * mass.cannon / that.amount / that.unit.cannon,
                                   space = space,
                                   time1 = time1,
                                   time2 = time2)
        }

    operator fun div(that: AccelerationAmount) =
        when
        {
            space.cannon / time1.cannon / time2.cannon == that.unit.cannon ->
                MassAmount(amount = amount / that.amount,
                           mass = mass)
            else                                                           ->
                MassAmount(amount = amount * space.cannon * time1.cannon * time2.cannon / that.amount / that.unit.cannon,
                           mass = mass)
        }

    operator fun times(that: TimeAmount) =
        when
        {
            time1.cannon == that.unit.cannon ->
                MomentumAmount(amount = amount * that.amount,
                               mass = mass,
                               space = space,
                               time = time2)
            time2.cannon == that.unit.cannon ->
                MomentumAmount(amount = amount * that.amount,
                               mass = mass,
                               space = space,
                               time = time1)
            else                             ->
                MomentumAmount(amount = amount / time2.cannon * that.amount * that.unit.cannon,
                               mass = mass,
                               space = space,
                               time = time1)
        }
}
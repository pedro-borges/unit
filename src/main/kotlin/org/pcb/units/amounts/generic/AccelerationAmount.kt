package org.pcb.units.amounts.generic

import org.pcb.units.domain.Acceleration
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.or
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class AccelerationAmount<S : Unit<Space>, T1 : Unit<Time>, T2 : Unit<Time>>(
        amount: Number,
        val space: S,
        val time1: T1,
        val time2: T2)
    : Amount<Acceleration>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               units =
                               if (time1.cannon == time2.cannon)
                                   mapOf(space to 1,
                                         time1 to -2)
                               else mapOf(space to 1,
                                          time1 to -1,
                                          time2 to -1),
                               domain = Acceleration::class))
{
    operator fun times(that: TimeAmount<*>) =
        SpeedAmount(amount = amount / time2.cannon * that.amount * that.unit.cannon,
                    space = space,
                    time = time1 or time2)

    @JvmName("timesT1")
    operator fun times(that: TimeAmount<T1>) =
        SpeedAmount(amount = amount * that.amount,
                    space = space,
                    time = time2)

    @JvmName("timesT2")
    operator fun times(that: TimeAmount<T2>) =
        SpeedAmount(amount = amount * that.amount,
                    space = space,
                    time = time1)

    operator fun times(that: MassAmount<*>) =
        ForceAmount(amount = amount * that.amount,
                    mass = that.unit,
                    space = space,
                    time1 = time1,
                    time2 = time2)
}
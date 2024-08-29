package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.div
import org.pcb.units.domain.Pace
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class PaceAmount<T : Unit<Time>, S : Unit<Space>>(
        amount: Number,
        val time: T,
        val space: S)
    : Amount<Pace>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Pace::class,
                       units = listOf(time to 1,
                                      space to -1)))
{
    // region Pace
        
    operator fun plus(that: PaceAmount<*, *>) =
        PaceAmount(amount = amount + that.amount * convert(that.time to time,
                                                           that.space to space),
                   time = time,
                   space = space)

    @JvmName("plusTS")    
    operator fun plus(that: PaceAmount<T, S>) =
        PaceAmount(amount = amount + that.amount,
                   time = time,
                   space = space)
        
    operator fun minus(that: PaceAmount<*, *>) =
        PaceAmount(amount = amount - that.amount * convert(that.time to time,
                                                           that.space to space),
                   time = time,
                   space = space)

    @JvmName("minusTS")    
    operator fun minus(that: PaceAmount<T, S>) =
        PaceAmount(amount = amount - that.amount,
                   time = time,
                   space = space)
        
    // endregion

    // region Scalar

    operator fun div(that: PaceAmount<*, *>) =
        ScalarAmount(amount = amount / that.amount / convert(that.time to time,
                                                             that.space to space))

    @JvmName("divTS")
    operator fun div(that: PaceAmount<T, S>) =
        ScalarAmount(amount = amount / that.amount)

    operator fun times(that: SpeedAmount<*, *>) =
        ScalarAmount(amount = amount * that.amount * convert(that.time to time,
                                                             that.space to space))

    @JvmName("timesST")
    operator fun div(that: SpeedAmount<S, T>) =
        ScalarAmount(amount = amount / that.amount)

    // endregion

    // region Time

    operator fun times(that: SpaceAmount<*>) =
        TimeAmount(amount = amount * that.amount * convert(that.space to space),
                   time = time)

    @JvmName("timesS")
    operator fun times(that: SpaceAmount<S>) =
        TimeAmount(amount = amount * that.amount,
                   time = time)

    // endregion
}
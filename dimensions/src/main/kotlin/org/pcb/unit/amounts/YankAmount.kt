package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.dimensions.Yank
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class YankAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, T>)
    : Amount<Yank>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Yank::class,
                       units = mapOf(unit.major.first to 1,
                                     unit.major.second to 1,
                                     unit.minor to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Yank>) =
            YankAmount(amount = amount * unit.scale,
                       unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Yank

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        YankAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        YankAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = U2(distance, mass) to time)

    @JvmName("plusYank")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: YankAmount<D, M, T>) =
        YankAmount(amount = amount + that.amount
                            * convert(that.distance to distance,
                                      that.mass to mass)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun plus(that: YankAmount<D, M, T>) =
        YankAmount(amount = amount + that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("minusYank")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: YankAmount<D, M, T>) =
        YankAmount(amount = amount - that.amount
                            * convert(that.distance to distance,
                                      that.mass to mass)
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun minus(that: YankAmount<D, M, T>) =
        YankAmount(amount = amount - that.amount,
                   unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divYank")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: YankAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert(that.time to time),
                           unit = distance to time)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Force

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount / that.amount
                             * convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun div(that: FrequencyAmount<T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: TimeAmount<T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<D, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: ForceAmount<D, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Jerk

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        JerkAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        JerkAmount(amount = amount / that.amount,
                   unit = distance to time)

    // endregion

    // region -> Mass

    @JvmName("divJerk")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<D, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: JerkAmount<D, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance)
                                    * convert(that.time to time,
                                              that.time to time),
                           unit = mass to time)

    operator fun div(that: AccelerationAmount<D, T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Power

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: DistanceAmount<D>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion
}

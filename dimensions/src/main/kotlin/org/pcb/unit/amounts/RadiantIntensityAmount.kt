package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.RadiantIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class RadiantIntensityAmount<A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, U2<A, T>>)
    : Amount<RadiantIntensity>(amount = amount.toBigDecimal(),
                               unit = CompositeUnit(
                                   domain = RadiantIntensity::class,
                                   units = mapOf(unit.major.first to 2,
                                                 unit.major.second to 1,
                                                 unit.minor.first to -2,
                                                 unit.minor.second to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val angle = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<RadiantIntensity>) =
            RadiantIntensityAmount(amount = amount * unit.scale,
                                   unit = U2(METER, kilo(GRAM)) to U2(RADIAN, SECOND))
    }

    // region -> RadiantIntensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        RadiantIntensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                               unit = U2(distance, mass) to U2(angle, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        RadiantIntensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                               unit = U2(distance, mass) to U2(angle, time))

    @JvmName("plusRadiantIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: RadiantIntensityAmount<A, D, M, T>) =
        RadiantIntensityAmount(amount = amount + that.amount
                                        * convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        / convert(that.angle to angle,
                                                  that.angle to angle,
                                                  that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = U2(distance, mass) to U2(angle, time))

    operator fun plus(that: RadiantIntensityAmount<A, D, M, T>) =
        RadiantIntensityAmount(amount = amount + that.amount,
                               unit = U2(distance, mass) to U2(angle, time))

    @JvmName("minusRadiantIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: RadiantIntensityAmount<A, D, M, T>) =
        RadiantIntensityAmount(amount = amount - that.amount
                                        * convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        / convert(that.angle to angle,
                                                  that.angle to angle,
                                                  that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = U2(distance, mass) to U2(angle, time))

    operator fun minus(that: RadiantIntensityAmount<A, D, M, T>) =
        RadiantIntensityAmount(amount = amount - that.amount,
                               unit = U2(distance, mass) to U2(angle, time))

    // endregion

    // region -> Scalar

    @JvmName("divRadiantIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: RadiantIntensityAmount<A, D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.angle to angle,
                                        that.angle to angle,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: RadiantIntensityAmount<A, D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("divSpectralIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: SpectralIntensityAmount<A, D, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.angle to angle,
                                          that.angle to angle,
                                          that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: SpectralIntensityAmount<A, D, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Power

    @JvmName("timesSolidAngle")
    operator fun <A : Unit<Angle>> times(that: SolidAngleAmount<A>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.angle to angle,
                                       that.angle to angle),
                    unit = U2(distance, mass) to time)

    operator fun times(that: SolidAngleAmount<A>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> SpectralIntensity

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        SpectralIntensityAmount(amount = amount / that.amount
                                         / convert(that.distance to distance),
                                unit = U2(distance, mass) to U2(angle, time))

    operator fun div(that: DistanceAmount<D>) =
        SpectralIntensityAmount(amount = amount / that.amount,
                                unit = U2(distance, mass) to U2(angle, time))

    @JvmName("timesSpacialFrequency")
    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
        SpectralIntensityAmount(amount = amount * that.amount
                                         / convert(that.distance to distance),
                                unit = U2(distance, mass) to U2(angle, time))

    operator fun times(that: SpacialFrequencyAmount<D>) =
        SpectralIntensityAmount(amount = amount * that.amount,
                                unit = U2(distance, mass) to U2(angle, time))

    // endregion
}

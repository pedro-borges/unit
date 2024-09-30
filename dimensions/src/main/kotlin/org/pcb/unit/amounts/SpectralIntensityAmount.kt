package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SpectralIntensity
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpectralIntensityAmount<BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, U2<BAC, T>>)
    : Amount<SpectralIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = SpectralIntensity::class,
                                    units = mapOf(unit.major.first to 1,
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
        operator fun invoke(amount: Number, unit: Unit<SpectralIntensity>) =
            SpectralIntensityAmount(amount = amount * unit.scale,
                                    unit = U2(METER, kilo(GRAM)) to U2(RADIAN, SECOND))
    }

    // region -> SpectralIntensity

    override operator fun times(that: Number) =
        SpectralIntensityAmount(amount = amount * that.toBigDecimal(),
                                unit = U2(distance, mass) to U2(angle, time))

    override operator fun div(that: Number) =
        SpectralIntensityAmount(amount = amount / that.toBigDecimal(),
                                unit = U2(distance, mass) to U2(angle, time))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpectralIntensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = U2(distance, mass) to U2(angle, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpectralIntensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = U2(distance, mass) to U2(angle, time))

    @JvmName("plusSpectralIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: SpectralIntensityAmount<BAC, L, M, T>) =
        SpectralIntensityAmount(amount = amount + that.amount
                                         * convert(that.distance to distance,
                                                   that.mass to mass)
                                         / convert(that.angle to angle,
                                                   that.angle to angle,
                                                   that.time to time,
                                                   that.time to time,
                                                   that.time to time),
                                unit = U2(distance, mass) to U2(angle, time))

    operator fun plus(that: SpectralIntensityAmount<BAC, L, M, T>) =
        SpectralIntensityAmount(amount = amount + that.amount,
                                unit = U2(distance, mass) to U2(angle, time))

    @JvmName("minusSpectralIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: SpectralIntensityAmount<BAC, L, M, T>) =
        SpectralIntensityAmount(amount = amount - that.amount
                                         * convert(that.distance to distance,
                                                   that.mass to mass)
                                         / convert(that.angle to angle,
                                                   that.angle to angle,
                                                   that.time to time,
                                                   that.time to time,
                                                   that.time to time),
                                unit = U2(distance, mass) to U2(angle, time))

    operator fun minus(that: SpectralIntensityAmount<BAC, L, M, T>) =
        SpectralIntensityAmount(amount = amount - that.amount,
                                unit = U2(distance, mass) to U2(angle, time))

    // endregion

    // region -> Scalar

    @JvmName("divSpectralIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: SpectralIntensityAmount<BAC, L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.angle to angle,
                                        that.angle to angle,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: SpectralIntensityAmount<BAC, L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> RadiantIntensity

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        RadiantIntensityAmount(amount = amount * that.amount
                                        * convert(that.distance to distance),
                               unit = U2(distance, mass) to U2(angle, time))

    operator fun times(that: DistanceAmount<L>) =
        RadiantIntensityAmount(amount = amount * that.amount,
                               unit = U2(distance, mass) to U2(angle, time))

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        RadiantIntensityAmount(amount = amount / that.amount
                                        * convert(that.distance to distance),
                               unit = U2(distance, mass) to U2(angle, time))

    operator fun div(that: SpacialFrequencyAmount<L>) =
        RadiantIntensityAmount(amount = amount / that.amount,
                               unit = U2(distance, mass) to U2(angle, time))

    // endregion

    // region -> SpacialFrequency

    @JvmName("divRadiantIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: RadiantIntensityAmount<BAC, L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        * convert(that.angle to angle,
                                                  that.angle to angle,
                                                  that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: RadiantIntensityAmount<BAC, L, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Yank

    @JvmName("timesSolidAngle")
    operator fun <BAC : Unit<Angle>> times(that: SolidAngleAmount<BAC>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.angle to angle,
                                      that.angle to angle),
                   unit = U2(distance, mass) to time)

    operator fun times(that: SolidAngleAmount<BAC>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}

package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SolidAngle
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SolidAngleAmount<A : Unit<Angle>>(
        amount: Number,
        unit: A)
    : Amount<SolidAngle>(amount = amount.toBigDecimal(),
                         unit = CompositeUnit(
                             domain = SolidAngle::class,
                             units = mapOf(unit to 2)))
{
    val angle = unit

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SolidAngle>) =
            SolidAngleAmount(amount = amount * unit.scale,
                             unit = RADIAN)
    }

    // region -> SolidAngle

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SolidAngleAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                         unit = angle)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SolidAngleAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                         unit = angle)

    @JvmName("plusSolidAngle")
    operator fun <A : Unit<Angle>> plus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount + that.amount
                                  * convert(that.angle to angle,
                                            that.angle to angle),
                         unit = angle)

    operator fun plus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount + that.amount,
                         unit = angle)

    @JvmName("minusSolidAngle")
    operator fun <A : Unit<Angle>> minus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount - that.amount
                                  * convert(that.angle to angle,
                                            that.angle to angle),
                         unit = angle)

    operator fun minus(that: SolidAngleAmount<A>) =
        SolidAngleAmount(amount = amount - that.amount,
                         unit = angle)

    // endregion

    // region -> Scalar

    @JvmName("divSolidAngle")
    operator fun <A : Unit<Angle>> div(that: SolidAngleAmount<A>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle,
                                        that.angle to angle),
                     unit = NUMERIC)

    operator fun div(that: SolidAngleAmount<A>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Angle

    @JvmName("divAngle")
    operator fun <A : Unit<Angle>> div(that: AngleAmount<A>) =
        AngleAmount(amount = amount / that.amount
                             / convert(that.angle to angle),
                    unit = angle)

    operator fun div(that: AngleAmount<A>) =
        AngleAmount(amount = amount / that.amount,
                    unit = angle)

    // endregion

    // region -> Illuminance

    operator fun <D : Unit<Distance>, LI : Unit<LuminousIntensity>> times(that: LuminanceAmount<D, LI>) =
        IlluminanceAmount(amount = amount * that.amount,
                          unit = U2(angle, that.luminousIntensity) to that.distance)

    // endregion

    // region -> LuminousFlux

    operator fun <LI : Unit<LuminousIntensity>> times(that: LuminousIntensityAmount<LI>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(angle, that.luminousIntensity))

    // endregion

    // region -> Power

    @JvmName("timesRadiantIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<A, D, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.angle to angle,
                                       that.angle to angle),
                    unit = U2(that.distance, that.mass) to that.time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<A, D, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> Yank

    @JvmName("timesSpectralIntensity")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<A, D, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.angle to angle,
                                      that.angle to angle),
                   unit = U2(that.distance, that.mass) to that.time)

    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<A, D, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, that.mass) to that.time)

    // endregion
}

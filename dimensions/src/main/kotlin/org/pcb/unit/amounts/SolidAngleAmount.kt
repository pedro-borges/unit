package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Radian.RADIAN
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

open class SolidAngleAmount<BAC : Unit<Angle>>(
        amount: Number,
        unit: BAC)
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

    // region -> Convert

    fun convertTo(unit: Unit<SolidAngle>) =
        SolidAngleAmount(amount = amount * convert(this.unit to unit),
                         unit = unit)

    // endregion

    // region -> SolidAngle

    override operator fun times(that: Number) =
        SolidAngleAmount(amount = amount * that.toBigDecimal(),
                         unit = angle)

    override operator fun div(that: Number) =
        SolidAngleAmount(amount = amount / that.toBigDecimal(),
                         unit = angle)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SolidAngleAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                         unit = angle)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SolidAngleAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                         unit = angle)

    @JvmName("plusSolidAngle")
    operator fun <BAC : Unit<Angle>> plus(that: SolidAngleAmount<BAC>) =
        SolidAngleAmount(amount = amount + that.amount
                                  * convert(that.angle to angle,
                                            that.angle to angle),
                         unit = angle)

    operator fun plus(that: SolidAngleAmount<BAC>) =
        SolidAngleAmount(amount = amount + that.amount,
                         unit = angle)

    @JvmName("minusSolidAngle")
    operator fun <BAC : Unit<Angle>> minus(that: SolidAngleAmount<BAC>) =
        SolidAngleAmount(amount = amount - that.amount
                                  * convert(that.angle to angle,
                                            that.angle to angle),
                         unit = angle)

    operator fun minus(that: SolidAngleAmount<BAC>) =
        SolidAngleAmount(amount = amount - that.amount,
                         unit = angle)

    // endregion

    // region -> Scalar

    @JvmName("divSolidAngle")
    operator fun <BAC : Unit<Angle>> div(that: SolidAngleAmount<BAC>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle,
                                        that.angle to angle),
                     unit = NUMERIC)

    operator fun div(that: SolidAngleAmount<BAC>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Angle

    @JvmName("divAngle")
    operator fun <BAC : Unit<Angle>> div(that: AngleAmount<BAC>) =
        AngleAmount(amount = amount / that.amount
                             / convert(that.angle to angle),
                    unit = angle)

    operator fun div(that: AngleAmount<BAC>) =
        AngleAmount(amount = amount / that.amount,
                    unit = angle)

    // endregion

    // region -> Illuminance

    operator fun <L : Unit<Distance>, J : Unit<LuminousIntensity>> times(that: LuminanceAmount<L, J>) =
        IlluminanceAmount(amount = amount * that.amount,
                          unit = U2(angle, that.luminousIntensity) to that.distance)

    // endregion

    // region -> LuminousFlux

    operator fun <J : Unit<LuminousIntensity>> times(that: LuminousIntensityAmount<J>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(angle, that.luminousIntensity))

    // endregion

    // region -> Power

    @JvmName("timesRadiantIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<BAC, L, M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.angle to angle,
                                       that.angle to angle),
                    unit = U2(that.distance, that.mass) to that.time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: RadiantIntensityAmount<BAC, L, M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.distance, that.mass) to that.time)

    // endregion

    // region -> Yank

    @JvmName("timesSpectralIntensity")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<BAC, L, M, T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.angle to angle,
                                      that.angle to angle),
                   unit = U2(that.distance, that.mass) to that.time)

    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: SpectralIntensityAmount<BAC, L, M, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, that.mass) to that.time)

    // endregion
}

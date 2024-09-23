package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousIntensityAmount<J : Unit<LuminousIntensity>>(
        amount: Number,
        unit: J)
    : Amount<LuminousIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = LuminousIntensity::class,
                                    units = mapOf(unit to 1)))
{
    val luminousIntensity = unit

    // region -> LuminousIntensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminousIntensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = luminousIntensity)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminousIntensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = luminousIntensity)

    @JvmName("plusLuminousIntensity")
    operator fun <J : Unit<LuminousIntensity>> plus(that: LuminousIntensityAmount<J>) =
        LuminousIntensityAmount(amount = amount + that.amount
                                         * convert(that.luminousIntensity to luminousIntensity),
                                unit = luminousIntensity)

    operator fun plus(that: LuminousIntensityAmount<J>) =
        LuminousIntensityAmount(amount = amount + that.amount,
                                unit = luminousIntensity)

    @JvmName("minusLuminousIntensity")
    operator fun <J : Unit<LuminousIntensity>> minus(that: LuminousIntensityAmount<J>) =
        LuminousIntensityAmount(amount = amount - that.amount
                                         * convert(that.luminousIntensity to luminousIntensity),
                                unit = luminousIntensity)

    operator fun minus(that: LuminousIntensityAmount<J>) =
        LuminousIntensityAmount(amount = amount - that.amount,
                                unit = luminousIntensity)

    // endregion

    // region -> Scalar

    @JvmName("divLuminousIntensity")
    operator fun <J : Unit<LuminousIntensity>> div(that: LuminousIntensityAmount<J>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.luminousIntensity to luminousIntensity),
                     unit = NUMERIC)

    operator fun div(that: LuminousIntensityAmount<J>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("divLuminance")
    operator fun <L : Unit<Distance>, J : Unit<LuminousIntensity>> div(that: LuminanceAmount<L, J>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.luminousIntensity to luminousIntensity),
                   unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: LuminanceAmount<L, J>) =
        AreaAmount(amount = amount / that.amount,
                   unit = that.distance)

    // endregion

    // region -> Luminance

    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        LuminanceAmount(amount = amount / that.amount,
                        unit = luminousIntensity to that.distance)

    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        LuminanceAmount(amount = amount * that.amount,
                        unit = luminousIntensity to that.distance)

    // endregion

    // region -> LuminousFlux

    operator fun <BAC : Unit<Angle>> times(that: SolidAngleAmount<BAC>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(that.angle, luminousIntensity))

    // endregion
}

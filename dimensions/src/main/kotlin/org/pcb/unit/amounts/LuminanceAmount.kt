package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Candela.CANDELA
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Luminance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminanceAmount<L : Unit<Distance>, J : Unit<LuminousIntensity>>(
        amount: Number,
        unit: Pair<J, L>)
    : Amount<Luminance>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Luminance::class,
                            units = mapOf(unit.major to 1,
                                          unit.minor to -2)))
{
    val luminousIntensity = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Luminance>) =
            LuminanceAmount(amount = amount * unit.scale,
                            unit = CANDELA to METER)
    }

    // region -> Luminance

    override operator fun times(that: Number) =
        LuminanceAmount(amount = amount * that.toBigDecimal(),
                        unit = luminousIntensity to distance)

    override operator fun div(that: Number) =
        LuminanceAmount(amount = amount / that.toBigDecimal(),
                        unit = luminousIntensity to distance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = luminousIntensity to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = luminousIntensity to distance)

    @JvmName("plusLuminance")
    operator fun <L : Unit<Distance>, J : Unit<LuminousIntensity>> plus(that: LuminanceAmount<L, J>) =
        LuminanceAmount(amount = amount + that.amount
                                 * convert(that.luminousIntensity to luminousIntensity)
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = luminousIntensity to distance)

    operator fun plus(that: LuminanceAmount<L, J>) =
        LuminanceAmount(amount = amount + that.amount,
                        unit = luminousIntensity to distance)

    @JvmName("minusLuminance")
    operator fun <L : Unit<Distance>, J : Unit<LuminousIntensity>> minus(that: LuminanceAmount<L, J>) =
        LuminanceAmount(amount = amount - that.amount
                                 * convert(that.luminousIntensity to luminousIntensity)
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = luminousIntensity to distance)

    operator fun minus(that: LuminanceAmount<L, J>) =
        LuminanceAmount(amount = amount - that.amount,
                        unit = luminousIntensity to distance)

    // endregion

    // region -> Scalar

    @JvmName("divLuminance")
    operator fun <L : Unit<Distance>, J : Unit<LuminousIntensity>> div(that: LuminanceAmount<L, J>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.luminousIntensity to luminousIntensity)
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: LuminanceAmount<L, J>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divLuminousIntensity")
    operator fun <J : Unit<LuminousIntensity>> div(that: LuminousIntensityAmount<J>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.luminousIntensity to luminousIntensity),
                             unit = NUMERIC to distance)

    operator fun div(that: LuminousIntensityAmount<J>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Illuminance

    operator fun <BAC : Unit<Angle>> times(that: SolidAngleAmount<BAC>) =
        IlluminanceAmount(amount = amount * that.amount,
                          unit = U2(that.angle, luminousIntensity) to distance)

    // endregion

    // region -> LuminousIntensity

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        LuminousIntensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = luminousIntensity)

    operator fun times(that: AreaAmount<L>) =
        LuminousIntensityAmount(amount = amount * that.amount,
                                unit = luminousIntensity)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        LuminousIntensityAmount(amount = amount / that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = luminousIntensity)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        LuminousIntensityAmount(amount = amount / that.amount,
                                unit = luminousIntensity)

    // endregion
}

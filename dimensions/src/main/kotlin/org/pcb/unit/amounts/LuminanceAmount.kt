package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
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

open class LuminanceAmount<D : Unit<Distance>, LI : Unit<LuminousIntensity>>(
        amount: Number,
        unit: Pair<LI, D>)
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

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = luminousIntensity to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = luminousIntensity to distance)

    @JvmName("plusLuminance")
    operator fun <D : Unit<Distance>, LI : Unit<LuminousIntensity>> plus(that: LuminanceAmount<D, LI>) =
        LuminanceAmount(amount = amount + that.amount
                                 * convert(that.luminousIntensity to luminousIntensity)
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = luminousIntensity to distance)

    operator fun plus(that: LuminanceAmount<D, LI>) =
        LuminanceAmount(amount = amount + that.amount,
                        unit = luminousIntensity to distance)

    @JvmName("minusLuminance")
    operator fun <D : Unit<Distance>, LI : Unit<LuminousIntensity>> minus(that: LuminanceAmount<D, LI>) =
        LuminanceAmount(amount = amount - that.amount
                                 * convert(that.luminousIntensity to luminousIntensity)
                                 / convert(that.distance to distance,
                                           that.distance to distance),
                        unit = luminousIntensity to distance)

    operator fun minus(that: LuminanceAmount<D, LI>) =
        LuminanceAmount(amount = amount - that.amount,
                        unit = luminousIntensity to distance)

    // endregion

    // region -> Scalar

    @JvmName("divLuminance")
    operator fun <D : Unit<Distance>, LI : Unit<LuminousIntensity>> div(that: LuminanceAmount<D, LI>) =
        Numeric(amount = amount / that.amount
                         / convert(that.luminousIntensity to luminousIntensity)
                         * convert(that.distance to distance,
                                   that.distance to distance))

    operator fun div(that: LuminanceAmount<D, LI>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Illuminance

    operator fun <A : Unit<Angle>> times(that: SolidAngleAmount<A>) =
        IlluminanceAmount(amount = amount * that.amount,
                          unit = U2(that.angle, luminousIntensity) to distance)

    // endregion

    // region -> LuminousIntensity

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        LuminousIntensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance),
                                unit = luminousIntensity)

    operator fun times(that: AreaAmount<D>) =
        LuminousIntensityAmount(amount = amount * that.amount,
                                unit = luminousIntensity)

    // endregion
}

package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Candela.CANDELA
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Illuminance
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class IlluminanceAmount<BAC : Unit<Angle>, L : Unit<Distance>, J : Unit<LuminousIntensity>>(
        amount: Number,
        unit: Pair<U2<BAC, J>, L>)
    : Amount<Illuminance>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = Illuminance::class,
                              units = mapOf(unit.major.first to 2,
                                            unit.major.second to 1,
                                            unit.minor to -2)))
{
    val angle = unit.major.first
    val luminousIntensity = unit.major.second
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Illuminance>) =
            IlluminanceAmount(amount = amount * unit.scale,
                              unit = U2(RADIAN, CANDELA) to METER)
    }

    // region -> Convert

    fun convertTo(unit: Unit<Illuminance>) =
        IlluminanceAmount(amount = amount * convert(this.unit to unit),
                          unit = unit)

    // endregion

    // region -> Illuminance

    override operator fun times(that: Number) =
        IlluminanceAmount(amount = amount * that.toBigDecimal(),
                          unit = U2(angle, luminousIntensity) to distance)

    override operator fun div(that: Number) =
        IlluminanceAmount(amount = amount / that.toBigDecimal(),
                          unit = U2(angle, luminousIntensity) to distance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        IlluminanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = U2(angle, luminousIntensity) to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        IlluminanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = U2(angle, luminousIntensity) to distance)

    @JvmName("plusIlluminance")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, J : Unit<LuminousIntensity>> plus(that: IlluminanceAmount<BAC, L, J>) =
        IlluminanceAmount(amount = amount + that.amount
                                   * convert(that.angle to angle,
                                             that.angle to angle,
                                             that.luminousIntensity to luminousIntensity)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = U2(angle, luminousIntensity) to distance)

    operator fun plus(that: IlluminanceAmount<BAC, L, J>) =
        IlluminanceAmount(amount = amount + that.amount,
                          unit = U2(angle, luminousIntensity) to distance)

    @JvmName("minusIlluminance")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, J : Unit<LuminousIntensity>> minus(that: IlluminanceAmount<BAC, L, J>) =
        IlluminanceAmount(amount = amount - that.amount
                                   * convert(that.angle to angle,
                                             that.angle to angle,
                                             that.luminousIntensity to luminousIntensity)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = U2(angle, luminousIntensity) to distance)

    operator fun minus(that: IlluminanceAmount<BAC, L, J>) =
        IlluminanceAmount(amount = amount - that.amount,
                          unit = U2(angle, luminousIntensity) to distance)

    // endregion

    // region -> Scalar

    @JvmName("divIlluminance")
    operator fun <BAC : Unit<Angle>, L : Unit<Distance>, J : Unit<LuminousIntensity>> div(that: IlluminanceAmount<BAC, L, J>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle,
                                        that.angle to angle,
                                        that.luminousIntensity to luminousIntensity)
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: IlluminanceAmount<BAC, L, J>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divLuminousFlux")
    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>> div(that: LuminousFluxAmount<BAC, J>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.angle to angle,
                                                that.angle to angle,
                                                that.luminousIntensity to luminousIntensity),
                             unit = NUMERIC to distance)

    operator fun div(that: LuminousFluxAmount<BAC, J>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Luminance

    @JvmName("divSolidAngle")
    operator fun <BAC : Unit<Angle>> div(that: SolidAngleAmount<BAC>) =
        LuminanceAmount(amount = amount / that.amount
                                 / convert(that.angle to angle,
                                           that.angle to angle),
                        unit = luminousIntensity to distance)

    operator fun div(that: SolidAngleAmount<BAC>) =
        LuminanceAmount(amount = amount / that.amount,
                        unit = luminousIntensity to distance)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        LuminousFluxAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = U2(angle, luminousIntensity))

    operator fun times(that: AreaAmount<L>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(angle, luminousIntensity))

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        LuminousFluxAmount(amount = amount / that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = U2(angle, luminousIntensity))

    operator fun div(that: FuelEfficiencyAmount<L>) =
        LuminousFluxAmount(amount = amount / that.amount,
                           unit = U2(angle, luminousIntensity))

    // endregion

    // region -> SolidAngle

    @JvmName("divLuminance")
    operator fun <L : Unit<Distance>, J : Unit<LuminousIntensity>> div(that: LuminanceAmount<L, J>) =
        SolidAngleAmount(amount = amount / that.amount
                                  / convert(that.luminousIntensity to luminousIntensity)
                                  * convert(that.distance to distance,
                                            that.distance to distance),
                         unit = angle)

    operator fun div(that: LuminanceAmount<L, J>) =
        SolidAngleAmount(amount = amount / that.amount,
                         unit = angle)

    // endregion
}

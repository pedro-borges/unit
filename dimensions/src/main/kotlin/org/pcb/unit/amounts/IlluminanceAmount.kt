package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
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

open class IlluminanceAmount<LI : Unit<LuminousIntensity>, A : Unit<Angle>, D : Unit<Distance>>(
        amount: Number,
        unit: Pair<U2<LI, A>, D>)
    : Amount<LuminousIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = LuminousIntensity::class,
                                    units = mapOf(unit.major.first to 1,
                                                  unit.major.second to 2,
                                                  unit.minor to -2)))
{
    val luminousIntensity = unit.major.first
    val angle = unit.major.second
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Illuminance>) =
            IlluminanceAmount(amount = amount * unit.scale,
                              unit = U2(CANDELA, RADIAN) to METER)
    }

    // region -> Illuminance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        IlluminanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = U2(luminousIntensity, angle) to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        IlluminanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = U2(luminousIntensity, angle) to distance)

    @JvmName("plusIlluminance")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>, D : Unit<Distance>> plus(that: IlluminanceAmount<LI, A, D>) =
        IlluminanceAmount(amount = amount + that.amount
                                   * convert(that.luminousIntensity to luminousIntensity,
                                             that.angle to angle,
                                             that.angle to angle)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = U2(luminousIntensity, angle) to distance)

    operator fun plus(that: IlluminanceAmount<LI, A, D>) =
        IlluminanceAmount(amount = amount + that.amount,
                          unit = U2(luminousIntensity, angle) to distance)

    @JvmName("minusIlluminance")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>, D : Unit<Distance>> minus(that: IlluminanceAmount<LI, A, D>) =
        IlluminanceAmount(amount = amount - that.amount
                                   * convert(that.luminousIntensity to luminousIntensity,
                                             that.angle to angle,
                                             that.angle to angle)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = U2(luminousIntensity, angle) to distance)

    operator fun minus(that: IlluminanceAmount<LI, A, D>) =
        IlluminanceAmount(amount = amount - that.amount,
                          unit = U2(luminousIntensity, angle) to distance)

    // endregion

    // region -> Numeric

    @JvmName("divIlluminance")
    operator fun <LI : Unit<LuminousIntensity>, A : Unit<Angle>, D : Unit<Distance>> div(that: IlluminanceAmount<LI, A, D>) =
        Numeric(amount = amount / that.amount
                         / convert(that.luminousIntensity to luminousIntensity,
                                   that.angle to angle,
                                   that.angle to angle)
                         * convert(that.distance to distance,
                                   that.distance to distance))

    operator fun div(that: IlluminanceAmount<LI, A, D>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        LuminousFluxAmount(amount = amount * that.amount * convert(that.distance to distance,
                                                                   that.distance to distance),
                           unit = U2(luminousIntensity, angle))

    operator fun times(that: AreaAmount<D>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(luminousIntensity, angle))

    // endregion
}
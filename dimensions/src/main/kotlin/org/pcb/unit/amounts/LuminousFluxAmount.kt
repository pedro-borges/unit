package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Candela.CANDELA
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Radian.RADIAN
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.LuminousFlux
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousFluxAmount<A : Unit<Angle>, LI : Unit<LuminousIntensity>>(
        amount: Number,
        unit: U2<A, LI>)
    : Amount<LuminousIntensity>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = LuminousIntensity::class,
                                    units = mapOf(unit.first to 1,
                                                  unit.second to 2)))
{
    val angle = unit.first
    val luminousIntensity = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<LuminousFlux>) =
            LuminousFluxAmount(amount = amount * unit.scale,
                               unit = U2(RADIAN, CANDELA))
    }

    // region -> LuminousFlux

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminousFluxAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = U2(angle, luminousIntensity))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminousFluxAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = U2(angle, luminousIntensity))

    @JvmName("plusLuminousFlux")
    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> plus(that: LuminousFluxAmount<A, LI>) =
        LuminousFluxAmount(amount = amount + that.amount
                                    * convert(that.angle to angle,
                                              that.angle to angle,
                                              that.luminousIntensity to luminousIntensity),
                           unit = U2(angle, luminousIntensity))

    operator fun plus(that: LuminousFluxAmount<A, LI>) =
        LuminousFluxAmount(amount = amount + that.amount,
                           unit = U2(angle, luminousIntensity))

    @JvmName("minusLuminousFlux")
    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> minus(that: LuminousFluxAmount<A, LI>) =
        LuminousFluxAmount(amount = amount - that.amount
                                    * convert(that.angle to angle,
                                              that.angle to angle,
                                              that.luminousIntensity to luminousIntensity),
                           unit = U2(angle, luminousIntensity))

    operator fun minus(that: LuminousFluxAmount<A, LI>) =
        LuminousFluxAmount(amount = amount - that.amount,
                           unit = U2(angle, luminousIntensity))

    // endregion

    // region -> Scalar

    @JvmName("divLuminousFlux")
    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>> div(that: LuminousFluxAmount<A, LI>) =
        Numeric(amount = amount / that.amount
                         / convert(that.angle to angle,
                                   that.angle to angle,
                                   that.luminousIntensity to luminousIntensity))

    operator fun div(that: LuminousFluxAmount<A, LI>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Area

    @JvmName("divIlluminance")
    operator fun <A : Unit<Angle>, D : Unit<Distance>, LI : Unit<LuminousIntensity>> div(that: IlluminanceAmount<A, D, LI>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.angle to angle,
                                      that.angle to angle,
                                      that.luminousIntensity to luminousIntensity),
                   unit = that.distance)

    operator fun <D : Unit<Distance>> div(that: IlluminanceAmount<A, D, LI>) =
        AreaAmount(amount = amount / that.amount,
                   unit = that.distance)

    // endregion

    // region -> Frequency

    @JvmName("divLuminousEnergy")
    operator fun <A : Unit<Angle>, LI : Unit<LuminousIntensity>, T : Unit<Time>> div(that: LuminousEnergyAmount<A, LI, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.angle to angle,
                                           that.angle to angle,
                                           that.luminousIntensity to luminousIntensity),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: LuminousEnergyAmount<A, LI, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> Illuminance

    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        IlluminanceAmount(amount = amount / that.amount,
                          unit = U2(angle, luminousIntensity) to that.distance)

    // endregion

    // region -> LuminousEnergy

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        LuminousEnergyAmount(amount = amount / that.amount,
                             unit = U3(angle, luminousIntensity, that.time))

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        LuminousEnergyAmount(amount = amount * that.amount,
                             unit = U3(angle, luminousIntensity, that.time))

    // endregion

    // region -> LuminousIntensity

    @JvmName("divSolidAngle")
    operator fun <A : Unit<Angle>> div(that: SolidAngleAmount<A>) =
        LuminousIntensityAmount(amount = amount / that.amount
                                         / convert(that.angle to angle,
                                                   that.angle to angle),
                                unit = luminousIntensity)

    operator fun div(that: SolidAngleAmount<A>) =
        LuminousIntensityAmount(amount = amount / that.amount,
                                unit = luminousIntensity)

    // endregion

    // region -> SolidAngle

    @JvmName("divLuminousIntensity")
    operator fun <LI : Unit<LuminousIntensity>> div(that: LuminousIntensityAmount<LI>) =
        SolidAngleAmount(amount = amount / that.amount
                                  / convert(that.luminousIntensity to luminousIntensity),
                         unit = angle)

    operator fun div(that: LuminousIntensityAmount<LI>) =
        SolidAngleAmount(amount = amount / that.amount,
                         unit = angle)

    // endregion
}

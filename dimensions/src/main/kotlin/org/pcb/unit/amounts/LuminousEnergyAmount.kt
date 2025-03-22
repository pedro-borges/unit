package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Candela.CANDELA
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Radian.RADIAN
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.LuminousEnergy
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class LuminousEnergyAmount<BAC : Unit<Angle>, J : Unit<LuminousIntensity>, T : Unit<Time>>(
        amount: Number,
        unit: U3<BAC, J, T>)
    : Amount<LuminousEnergy>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = LuminousEnergy::class,
                                 units = mapOf(unit.first to 2,
                                               unit.second to 1,
                                               unit.third to 1)))
{
    val angle = unit.first
    val luminousIntensity = unit.second
    val time = unit.third

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<LuminousEnergy>) =
            LuminousEnergyAmount(amount = amount * unit.scale,
                                 unit = U3(RADIAN, CANDELA, SECOND))
    }

    // region -> Convert

    fun convertTo(unit: Unit<LuminousEnergy>) =
        LuminousEnergyAmount(amount = amount * convert(this.unit to unit),
                             unit = unit)

    // endregion

    // region -> LuminousEnergy

    override operator fun times(that: Number) =
        LuminousEnergyAmount(amount = amount * that.toBigDecimal(),
                             unit = U3(angle, luminousIntensity, time))

    override operator fun div(that: Number) =
        LuminousEnergyAmount(amount = amount / that.toBigDecimal(),
                             unit = U3(angle, luminousIntensity, time))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        LuminousEnergyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = U3(angle, luminousIntensity, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        LuminousEnergyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = U3(angle, luminousIntensity, time))

    @JvmName("plusLuminousEnergy")
    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>, T : Unit<Time>> plus(that: LuminousEnergyAmount<BAC, J, T>) =
        LuminousEnergyAmount(amount = amount + that.amount
                                      * convert(that.angle to angle,
                                                that.angle to angle,
                                                that.luminousIntensity to luminousIntensity,
                                                that.time to time),
                             unit = U3(angle, luminousIntensity, time))

    operator fun plus(that: LuminousEnergyAmount<BAC, J, T>) =
        LuminousEnergyAmount(amount = amount + that.amount,
                             unit = U3(angle, luminousIntensity, time))

    @JvmName("minusLuminousEnergy")
    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>, T : Unit<Time>> minus(that: LuminousEnergyAmount<BAC, J, T>) =
        LuminousEnergyAmount(amount = amount - that.amount
                                      * convert(that.angle to angle,
                                                that.angle to angle,
                                                that.luminousIntensity to luminousIntensity,
                                                that.time to time),
                             unit = U3(angle, luminousIntensity, time))

    operator fun minus(that: LuminousEnergyAmount<BAC, J, T>) =
        LuminousEnergyAmount(amount = amount - that.amount,
                             unit = U3(angle, luminousIntensity, time))

    // endregion

    // region -> Scalar

    @JvmName("divLuminousEnergy")
    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>, T : Unit<Time>> div(that: LuminousEnergyAmount<BAC, J, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.angle to angle,
                                        that.angle to angle,
                                        that.luminousIntensity to luminousIntensity,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: LuminousEnergyAmount<BAC, J, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> LuminousFlux

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        LuminousFluxAmount(amount = amount * that.amount
                                    / convert(that.time to time),
                           unit = U2(angle, luminousIntensity))

    operator fun times(that: FrequencyAmount<T>) =
        LuminousFluxAmount(amount = amount * that.amount,
                           unit = U2(angle, luminousIntensity))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        LuminousFluxAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = U2(angle, luminousIntensity))

    operator fun div(that: TimeAmount<T>) =
        LuminousFluxAmount(amount = amount / that.amount,
                           unit = U2(angle, luminousIntensity))

    // endregion

    // region -> Time

    @JvmName("divLuminousFlux")
    operator fun <BAC : Unit<Angle>, J : Unit<LuminousIntensity>> div(that: LuminousFluxAmount<BAC, J>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.angle to angle,
                                      that.angle to angle,
                                      that.luminousIntensity to luminousIntensity),
                   unit = time)

    operator fun div(that: LuminousFluxAmount<BAC, J>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}

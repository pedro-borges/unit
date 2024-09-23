package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Mole.MOLE
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.CatalyticEfficiency
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class CatalyticEfficiencyAmount<L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<L, U2<N, T>>)
    : Amount<CatalyticEfficiency>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = CatalyticEfficiency::class,
                                      units = mapOf(unit.major to 3,
                                                    unit.minor.first to -1,
                                                    unit.minor.second to -1)))
{
    val distance = unit.major
    val substance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<CatalyticEfficiency>) =
            CatalyticEfficiencyAmount(amount = amount * unit.scale,
                                      unit = METER to U2(MOLE, SECOND))
    }

    // region -> CatalyticEfficiency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        CatalyticEfficiencyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = distance to U2(substance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        CatalyticEfficiencyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = distance to U2(substance, time))

    @JvmName("plusCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> plus(that: CatalyticEfficiencyAmount<L, N, T>) =
        CatalyticEfficiencyAmount(amount = amount + that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance,
                                                     that.distance to distance)
                                           / convert(that.substance to substance,
                                                     that.time to time),
                                  unit = distance to U2(substance, time))

    operator fun plus(that: CatalyticEfficiencyAmount<L, N, T>) =
        CatalyticEfficiencyAmount(amount = amount + that.amount,
                                  unit = distance to U2(substance, time))

    @JvmName("minusCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> minus(that: CatalyticEfficiencyAmount<L, N, T>) =
        CatalyticEfficiencyAmount(amount = amount - that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance,
                                                     that.distance to distance)
                                           / convert(that.substance to substance,
                                                     that.time to time),
                                  unit = distance to U2(substance, time))

    operator fun minus(that: CatalyticEfficiencyAmount<L, N, T>) =
        CatalyticEfficiencyAmount(amount = amount - that.amount,
                                  unit = distance to U2(substance, time))

    // endregion

    // region -> Scalar

    @JvmName("divCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> div(that: CatalyticEfficiencyAmount<L, N, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance)
                              * convert(that.substance to substance,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: CatalyticEfficiencyAmount<L, N, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Frequency

    @JvmName("divMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarVolumeAmount<L, N>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance)
                                 * convert(that.substance to substance),
                        unit = NUMERIC to time)

    operator fun div(that: MolarVolumeAmount<L, N>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    @JvmName("timesMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarityAmount<L, N>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.substance to substance)
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = NUMERIC to time)

    operator fun times(that: MolarityAmount<L, N>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MolarVolume

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MolarVolumeAmount(amount = amount / that.amount
                                   * convert(that.time to time),
                          unit = distance to substance)

    operator fun div(that: FrequencyAmount<T>) =
        MolarVolumeAmount(amount = amount / that.amount,
                          unit = distance to substance)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MolarVolumeAmount(amount = amount * that.amount
                                   * convert(that.time to time),
                          unit = distance to substance)

    operator fun times(that: TimeAmount<T>) =
        MolarVolumeAmount(amount = amount * that.amount,
                          unit = distance to substance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesSubstance")
    operator fun <N : Unit<Substance>> times(that: SubstanceAmount<N>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      * convert(that.substance to substance),
                             unit = distance to time)

    operator fun times(that: SubstanceAmount<N>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = distance to time)

    // endregion
}

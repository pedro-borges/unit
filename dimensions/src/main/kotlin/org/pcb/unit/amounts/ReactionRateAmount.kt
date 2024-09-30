package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Mole.MOLE
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.ReactionRate
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ReactionRateAmount<L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<N, U2<T, L>>)
    : Amount<ReactionRate>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = ReactionRate::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor.first to -1,
                                             unit.minor.second to -3)))
{
    val substance = unit.major
    val time = unit.minor.first
    val distance = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ReactionRate>) =
            ReactionRateAmount(amount = amount * unit.scale,
                               unit = MOLE to U2(SECOND, METER))
    }

    // region -> ReactionRate

    override operator fun times(that: Number) =
        ReactionRateAmount(amount = amount * that.toBigDecimal(),
                           unit = substance to U2(time, distance))

    override operator fun div(that: Number) =
        ReactionRateAmount(amount = amount / that.toBigDecimal(),
                           unit = substance to U2(time, distance))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ReactionRateAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = substance to U2(time, distance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ReactionRateAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = substance to U2(time, distance))

    @JvmName("plusReactionRate")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> plus(that: ReactionRateAmount<L, N, T>) =
        ReactionRateAmount(amount = amount + that.amount
                                    * convert(that.substance to substance)
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance,
                                              that.time to time),
                           unit = substance to U2(time, distance))

    operator fun plus(that: ReactionRateAmount<L, N, T>) =
        ReactionRateAmount(amount = amount + that.amount,
                           unit = substance to U2(time, distance))

    @JvmName("minusReactionRate")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> minus(that: ReactionRateAmount<L, N, T>) =
        ReactionRateAmount(amount = amount - that.amount
                                    * convert(that.substance to substance)
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance,
                                              that.time to time),
                           unit = substance to U2(time, distance))

    operator fun minus(that: ReactionRateAmount<L, N, T>) =
        ReactionRateAmount(amount = amount - that.amount,
                           unit = substance to U2(time, distance))

    // endregion

    // region -> Scalar

    @JvmName("divReactionRate")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> div(that: ReactionRateAmount<L, N, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.substance to substance)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ReactionRateAmount<L, N, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Frequency

    @JvmName("timesMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        FrequencyAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance)
                                 / convert(that.substance to substance),
                        unit = NUMERIC to time)

    operator fun times(that: MolarVolumeAmount<L, N>) =
        FrequencyAmount(amount = amount * that.amount,
                        unit = NUMERIC to time)

    @JvmName("divMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.substance to substance)
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.distance to distance),
                        unit = NUMERIC to time)

    operator fun div(that: MolarityAmount<L, N>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Molarity

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MolarityAmount(amount = amount / that.amount
                                * convert(that.time to time),
                       unit = substance to distance)

    operator fun div(that: FrequencyAmount<T>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = substance to distance)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MolarityAmount(amount = amount * that.amount
                                * convert(that.time to time),
                       unit = substance to distance)

    operator fun times(that: TimeAmount<T>) =
        MolarityAmount(amount = amount * that.amount,
                       unit = substance to distance)

    // endregion

    // region -> PowerDensity

    @JvmName("timesMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> times(that: MolarEnergyAmount<L, M, N, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance)
                                    / convert(that.substance to substance,
                                              that.time to time,
                                              that.time to time),
                           unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: MolarEnergyAmount<L, M, N, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(distance, time))

    // endregion
}

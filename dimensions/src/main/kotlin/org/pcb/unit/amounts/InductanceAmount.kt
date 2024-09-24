package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Inductance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class InductanceAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, U2<I, T>>)
    : Amount<Inductance>(amount = amount.toBigDecimal(),
                         unit = CompositeUnit(
                             domain = Inductance::class,
                             units = mapOf(unit.major.first to 2,
                                           unit.major.second to 1,
                                           unit.minor.first to -2,
                                           unit.minor.second to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val electricCurrent = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Inductance>) =
            InductanceAmount(amount = amount * unit.scale,
                             unit = U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
    }

    // region -> Inductance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        InductanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        InductanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("plusInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: InductanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount + that.amount
                                  * convert(that.distance to distance,
                                            that.distance to distance,
                                            that.mass to mass)
                                  / convert(that.electricCurrent to electricCurrent,
                                            that.electricCurrent to electricCurrent,
                                            that.time to time,
                                            that.time to time),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun plus(that: InductanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount + that.amount,
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("minusInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: InductanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount - that.amount
                                  * convert(that.distance to distance,
                                            that.distance to distance,
                                            that.mass to mass)
                                  / convert(that.electricCurrent to electricCurrent,
                                            that.electricCurrent to electricCurrent,
                                            that.time to time,
                                            that.time to time),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun minus(that: InductanceAmount<L, I, M, T>) =
        InductanceAmount(amount = amount - that.amount,
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("divInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: InductanceAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: InductanceAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("divPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: PermeabilityAmount<L, I, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.electricCurrent to electricCurrent,
                                          that.electricCurrent to electricCurrent,
                                          that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: PermeabilityAmount<L, I, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricResistance

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ElectricResistanceAmount(amount = amount * that.amount
                                          / convert(that.time to time),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: FrequencyAmount<T>) =
        ElectricResistanceAmount(amount = amount * that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          / convert(that.time to time),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: TimeAmount<T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<I>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    * convert(that.electricCurrent to electricCurrent),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: ElectricCurrentAmount<I>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Permeability

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        PermeabilityAmount(amount = amount / that.amount
                                    / convert(that.distance to distance),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: DistanceAmount<L>) =
        PermeabilityAmount(amount = amount / that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        PermeabilityAmount(amount = amount * that.amount
                                    / convert(that.distance to distance),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: SpacialFrequencyAmount<L>) =
        PermeabilityAmount(amount = amount * that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Time

    @JvmName("timesElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        TimeAmount(amount = amount * that.amount
                            * convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time)
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass),
                   unit = time)

    operator fun times(that: ElectricConductanceAmount<L, I, M, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    @JvmName("divElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: ElectricResistanceAmount<L, I, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}

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
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Permeability
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PermeabilityAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, U2<I, T>>)
    : Amount<Permeability>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = Permeability::class,
                               units = mapOf(unit.major.first to 1,
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
        operator fun invoke(amount: Number, unit: Unit<Permeability>) =
            PermeabilityAmount(amount = amount * unit.scale,
                               unit = U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
    }

    // region -> Permeability

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PermeabilityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PermeabilityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("plusPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: PermeabilityAmount<L, I, M, T>) =
        PermeabilityAmount(amount = amount + that.amount
                                    * convert(that.distance to distance,
                                              that.mass to mass)
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun plus(that: PermeabilityAmount<L, I, M, T>) =
        PermeabilityAmount(amount = amount + that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("minusPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: PermeabilityAmount<L, I, M, T>) =
        PermeabilityAmount(amount = amount - that.amount
                                    * convert(that.distance to distance,
                                              that.mass to mass)
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun minus(that: PermeabilityAmount<L, I, M, T>) =
        PermeabilityAmount(amount = amount - that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("divPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: PermeabilityAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: PermeabilityAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> ElectricResistance

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          / convert(that.time to time)
                                          * convert(that.distance to distance),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: PaceAmount<L, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ElectricResistanceAmount(amount = amount * that.amount
                                          * convert(that.distance to distance)
                                          / convert(that.time to time),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: SpeedAmount<L, T>) =
        ElectricResistanceAmount(amount = amount * that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Inductance

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        InductanceAmount(amount = amount * that.amount
                                  * convert(that.distance to distance),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: DistanceAmount<L>) =
        InductanceAmount(amount = amount * that.amount,
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        InductanceAmount(amount = amount / that.amount
                                  * convert(that.distance to distance),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: SpacialFrequencyAmount<L>) =
        InductanceAmount(amount = amount / that.amount,
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("timesMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFluxDensityAmount(amount = amount * that.amount
                                           * convert(that.electricCurrent to electricCurrent)
                                           / convert(that.distance to distance),
                                  unit = mass to U2(electricCurrent, time))

    operator fun times(that: MagneticFieldStrengthAmount<L, I>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = mass to U2(electricCurrent, time))

    // endregion

    // region -> Pace

    @JvmName("timesElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount
                            * convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time)
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass),
                   unit = time to distance)

    operator fun times(that: ElectricConductanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount,
                   unit = time to distance)

    @JvmName("divElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: ElectricResistanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: InductanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        * convert(that.electricCurrent to electricCurrent,
                                                  that.electricCurrent to electricCurrent,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: InductanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion
}

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
import org.pcb.unit.dimensions.ElectricResistance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricResistanceAmount<D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, U2<EC, T>>)
    : Amount<ElectricResistance>(amount = amount.toBigDecimal(),
                                 unit = CompositeUnit(
                                     domain = ElectricResistance::class,
                                     units = mapOf(unit.major.first to 2,
                                                   unit.major.second to 1,
                                                   unit.minor.first to -2,
                                                   unit.minor.second to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val electricCurrent = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricResistance>) =
            ElectricResistanceAmount(amount = amount * unit.scale,
                                     unit = U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
    }

    // region -> ElectricResistance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("plusElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount + that.amount
                                          * convert(that.distance to distance,
                                                    that.distance to distance,
                                                    that.mass to mass)
                                          / convert(that.electricCurrent to electricCurrent,
                                                    that.electricCurrent to electricCurrent,
                                                    that.time to time,
                                                    that.time to time,
                                                    that.time to time),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun plus(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount + that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("minusElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount - that.amount
                                          * convert(that.distance to distance,
                                                    that.distance to distance,
                                                    that.mass to mass)
                                          / convert(that.electricCurrent to electricCurrent,
                                                    that.electricCurrent to electricCurrent,
                                                    that.time to time,
                                                    that.time to time,
                                                    that.time to time),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun minus(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricResistanceAmount(amount = amount - that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("timesElectricConductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<D, EC, M, T>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time)
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass),
                     unit = NUMERIC)

    operator fun times(that: ElectricConductanceAmount<D, EC, M, T>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    @JvmName("divElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<D, EC, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ElectricResistanceAmount<D, EC, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> ElectricTension

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount * that.amount
                                       * convert(that.electricCurrent to electricCurrent),
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Frequency

    @JvmName("divInductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: InductanceAmount<D, EC, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.electricCurrent to electricCurrent,
                                           that.electricCurrent to electricCurrent,
                                           that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: InductanceAmount<D, EC, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Inductance

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        InductanceAmount(amount = amount / that.amount
                                  * convert(that.time to time),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: FrequencyAmount<T>) =
        InductanceAmount(amount = amount / that.amount,
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        InductanceAmount(amount = amount * that.amount
                                  * convert(that.time to time),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: TimeAmount<T>) =
        InductanceAmount(amount = amount * that.amount,
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> MagneticFlux

    @JvmName("timesElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    * convert(that.electricCurrent to electricCurrent,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Time

    @JvmName("timesCapacitance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: CapacitanceAmount<D, EC, M, T>) =
        TimeAmount(amount = amount * that.amount
                            * convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time)
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass),
                   unit = time)

    operator fun times(that: CapacitanceAmount<D, EC, M, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion
}

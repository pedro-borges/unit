package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.ElectricTension
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricTensionAmount<D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, U2<EC, T>>)
    : Amount<ElectricTension>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricTension::class,
                                  units = mapOf(unit.major.first to 2,
                                                unit.major.second to 1,
                                                unit.minor.first to -1,
                                                unit.minor.second to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val electricCurrent = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricTension>) =
            ElectricTensionAmount(amount = amount * unit.scale,
                                  unit = U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("plusElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount + that.amount
                                       * convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun plus(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount + that.amount,
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("minusElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount - that.amount
                                       * convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun minus(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricTensionAmount(amount = amount - that.amount,
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> ElectricCharge

    @JvmName("timesCapacitance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time)
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass),
                             unit = U2(electricCurrent, time))

    operator fun times(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesElectricConductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time)
                                       / convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass),
                              unit = electricCurrent)

    operator fun times(that: ElectricConductanceAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    @JvmName("divElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass)
                                       * convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun div(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          / convert(that.electricCurrent to electricCurrent),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent,
                                        that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divMagneticFlux")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<D, EC, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.electricCurrent to electricCurrent,
                                           that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: MagneticFluxAmount<D, EC, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MagneticFlux

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MagneticFluxAmount(amount = amount / that.amount
                                    * convert(that.time to time),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: FrequencyAmount<T>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MagneticFluxAmount(amount = amount * that.amount
                                    * convert(that.time to time),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: TimeAmount<T>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Power

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.electricCurrent to electricCurrent),
                    unit = U2(distance, mass) to time)

    operator fun times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divElectricTension")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.distance to distance,
                                   that.distance to distance,
                                   that.mass to mass)
                         * convert(that.electricCurrent to electricCurrent,
                                   that.time to time,
                                   that.time to time,
                                   that.time to time))

    operator fun div(that: ElectricTensionAmount<D, EC, M, T>) =
        Numeric(amount = amount / that.amount)

    // endregion
}

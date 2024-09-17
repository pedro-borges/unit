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
import org.pcb.unit.dimensions.Capacitance
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class CapacitanceAmount<D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<T, EC>, U2<M, D>>)
    : Amount<Capacitance>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = Capacitance::class,
                              units = mapOf(unit.major.first to 4,
                                            unit.major.second to 2,
                                            unit.minor.first to -1,
                                            unit.minor.second to -2)))
{
    val time = unit.major.first
    val electricCurrent = unit.major.second
    val mass = unit.minor.first
    val distance = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Capacitance>) =
            CapacitanceAmount(amount = amount * unit.scale,
                              unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
    }

    // region -> Capacitance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        CapacitanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        CapacitanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("plusCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> plus(that: CapacitanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount + that.amount
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time,
                                             that.time to time,
                                             that.electricCurrent to electricCurrent,
                                             that.electricCurrent to electricCurrent)
                                   / convert(that.mass to mass,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun plus(that: CapacitanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount + that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("minusCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> minus(that: CapacitanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount - that.amount
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time,
                                             that.time to time,
                                             that.electricCurrent to electricCurrent,
                                             that.electricCurrent to electricCurrent)
                                   / convert(that.mass to mass,
                                             that.distance to distance,
                                             that.distance to distance),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun minus(that: CapacitanceAmount<D, EC, M, T>) =
        CapacitanceAmount(amount = amount - that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> ElectricCharge

    @JvmName("timesElectricTension")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass)
                                      / convert(that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = U2(electricCurrent, time))

    operator fun times(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> ElectricConductance

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ElectricConductanceAmount(amount = amount * that.amount
                                           / convert(that.time to time),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: FrequencyAmount<T>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           / convert(that.time to time),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: TimeAmount<T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Scalar

    @JvmName("divCapacitance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: CapacitanceAmount<D, EC, M, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.electricCurrent to electricCurrent,
                                   that.electricCurrent to electricCurrent,
                                   that.time to time,
                                   that.time to time,
                                   that.time to time,
                                   that.time to time)
                         * convert(that.distance to distance,
                                   that.distance to distance,
                                   that.mass to mass))

    operator fun div(that: CapacitanceAmount<D, EC, M, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Time

    @JvmName("divElectricConductance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<D, EC, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time)
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass),
                   unit = time)

    operator fun div(that: ElectricConductanceAmount<D, EC, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    @JvmName("timesElectricResistance")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<D, EC, M, T>) =
        TimeAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun times(that: ElectricResistanceAmount<D, EC, M, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion

}

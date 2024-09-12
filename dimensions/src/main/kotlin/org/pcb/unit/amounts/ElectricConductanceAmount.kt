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
import org.pcb.unit.dimensions.ElectricConductance
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

open class ElectricConductanceAmount<T3 : Unit<Time>, EC2 : Unit<ElectricCurrent>, M : Unit<Mass>, D2 : Unit<Distance>>(
        amount: Number,
        unit: Pair<U2<T3, EC2>, U2<M, D2>>)
    : Amount<ElectricConductance>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = ElectricConductance::class,
                                      units = mapOf(unit.major.first to 3,
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
        operator fun invoke(amount: Number, unit: Unit<ElectricConductance>) =
            ElectricConductanceAmount(amount = amount * unit.scale,
                                      unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
    }

    // region -> ElectricConductance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricConductanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricConductanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("plusElectricConductance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> plus(that: ElectricConductanceAmount<T, EC, M, D>) =
        ElectricConductanceAmount(amount = amount + that.amount
                                           * convert(that.time to time,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent)
                                           / convert(that.mass to mass,
                                                     that.distance to distance,
                                                     that.distance to distance),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun plus(that: ElectricConductanceAmount<T3, EC2, M, D2>) =
        ElectricConductanceAmount(amount = amount + that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("minusElectricConductance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> minus(that: ElectricConductanceAmount<T, EC, M, D>) =
        ElectricConductanceAmount(amount = amount - that.amount
                                           * convert(that.time to time,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent)
                                           / convert(that.mass to mass,
                                                     that.distance to distance,
                                                     that.distance to distance),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun minus(that: ElectricConductanceAmount<T3, EC2, M, D2>) =
        ElectricConductanceAmount(amount = amount - that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Natural

    @JvmName("divElectricConductance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: ElectricConductanceAmount<T, EC, M, D>) =
        Numeric(amount = amount / that.amount
                         / convert(that.time to time,
                                   that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent,
                                   that.electricCurrent to electricCurrent)
                         * convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance))

    operator fun div(that: ElectricConductanceAmount<T3, EC2, M, D2>) =
        Numeric(amount = amount / that.amount)

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, D, T, EC>) =
        Numeric(amount = that.amount * amount
                         * convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         / convert(that.time to time,
                                   that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent,
                                   that.electricCurrent to electricCurrent))

    operator fun times(that: ElectricResistanceAmount<M, D2, T3, EC2>) =
        Numeric(amount * that.amount)

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun times(that: ElectricTensionAmount<M, D2, T3, EC2>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> Capacitance

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        CapacitanceAmount(amount = amount / that.amount / convert(that.time to time),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: FrequencyAmount<T3>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        CapacitanceAmount(amount = amount * that.amount * convert(that.time to time),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: TimeAmount<T3>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Frequency

    @JvmName("divCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: CapacitanceAmount<T, EC, M, D>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass,
                                           that.distance to distance,
                                           that.distance to distance)
                                 * convert(that.time to time,
                                           that.time to time,
                                           that.time to time,
                                           that.time to time,
                                           that.electricCurrent to electricCurrent,
                                           that.electricCurrent to electricCurrent),
                        unit = NUMERIC to time)

    operator fun div(that: CapacitanceAmount<T3, EC2, M, D2>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion
}

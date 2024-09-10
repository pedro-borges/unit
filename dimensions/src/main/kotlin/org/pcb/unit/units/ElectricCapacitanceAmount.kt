package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCapacitance
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric

open class ElectricCapacitanceAmount<T4 : Unit<Time>, EC2 : Unit<ElectricCurrent>, M : Unit<Mass>, D2 : Unit<Distance>>(
        amount: Number,
        unit: Pair<U2<T4, EC2>, U2<M, D2>>)
    : Amount<ElectricCharge>(amount = amount.toBigBigDecimal(),
                             unit = CompositeUnit(
                                 domain = ElectricCharge::class,
                                 units = listOf(unit.major.first to 4,
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
        operator fun invoke(amount: Number, unit: Unit<ElectricCapacitance>) =
            ElectricCapacitanceAmount(amount = amount * unit.scale,
                                      unit = U2(SECOND, AMPERE) to U2(KILOGRAM, METER))
    }

    // region -> ElectricCharge

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricCapacitanceAmount(amount = amount * that.amount * that.unit.scale,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricCapacitanceAmount(amount = amount / that.amount / that.unit.scale,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("plusElectricCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> plus(that: ElectricCapacitanceAmount<T, EC, M, D>) =
        ElectricCapacitanceAmount(amount = amount + that.amount
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

    operator fun plus(that: ElectricCapacitanceAmount<T4, EC2, M, D2>) =
        ElectricCapacitanceAmount(amount = amount + that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("minusElectricCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> minus(that: ElectricCapacitanceAmount<T, EC, M, D>) =
        ElectricCapacitanceAmount(amount = amount - that.amount
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

    operator fun minus(that: ElectricCapacitanceAmount<T4, EC2, M, D2>) =
        ElectricCapacitanceAmount(amount = amount - that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Natural

    @JvmName("divElectricCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: ElectricCapacitanceAmount<T, EC, M, D>) =
        Numeric(amount = amount / that.amount
                              / convert(that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent)
                              * convert(that.mass to mass,
                                        that.distance to distance,
                                        that.distance to distance))

    operator fun div(that: ElectricCapacitanceAmount<T4, EC2, M, D2>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      / convert(that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time)
                                      * convert(that.mass to mass,
                                                that.distance to distance,
                                                that.distance to distance),
                             unit = U2(electricCurrent, time))

    operator fun <M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricTensionAmount<M, D, T4, EC2>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> Time

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, D, T, EC>) =
        TimeAmount(amount = amount * that.amount
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent)
                            * convert(that.mass to mass,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = time)

    operator fun times(that: ElectricCapacitanceAmount<T4, EC2, M, D2>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion

    // region -> ElectricConductivity

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ElectricConductivityAmount(amount = amount * that.amount * convert(that.time to time),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: FrequencyAmount<T4>) =
        ElectricConductivityAmount(amount = amount * that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricConductivityAmount(amount = amount / that.amount / convert(that.time to time),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: TimeAmount<T4>) =
        ElectricConductivityAmount(amount = amount / that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Time

    @JvmName("divElectricConductivity")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> div(that: ElectricConductivityAmount<T, EC, M, D>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent)
                            * convert(that.mass to mass,
                                      that.distance to distance,
                                      that.distance to distance),
                   unit = time)

    operator fun div(that: ElectricConductivityAmount<T4, EC2, M, D2>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}

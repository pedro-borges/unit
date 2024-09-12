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

open class ElectricTensionAmount<M : Unit<Mass>, D2 : Unit<Distance>, T3 : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<U2<M, D2>, U2<T3, EC>>)
    : Amount<ElectricTension>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricTension::class,
                                  units = mapOf(unit.major.first to 1,
                                                unit.major.second to 2,
                                                unit.minor.first to -3,
                                                unit.minor.second to -1)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricTension>) =
            ElectricTensionAmount(amount = amount * unit.scale,
                                  unit = U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("plusElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricTensionAmount(amount = amount + that.amount
                                       * convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun plus(that: ElectricTensionAmount<M, D2, T3, EC>) =
        ElectricTensionAmount(amount = amount + that.amount,
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("minusElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricTensionAmount(amount = amount - that.amount
                                       * convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun minus(that: ElectricTensionAmount<M, D2, T3, EC>) =
        ElectricTensionAmount(amount = amount - that.amount,
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> Natural

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, D, T, EC>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         * convert(that.time to time,
                                   that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent))

    operator fun div(that: ElectricTensionAmount<M, D2, T3, EC>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Energy

    @JvmName("timesElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time,
                                                             that.electricCurrent to electricCurrent),
                     unit = U2(mass, distance) to time)

    operator fun times(that: ElectricChargeAmount<EC, T3>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, distance) to time)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    operator fun div(that: ElectricResistanceAmount<M, D2, T3, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    @JvmName("timesElectricConductance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: ElectricConductanceAmount<T, EC, M, D>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun times(that: ElectricConductanceAmount<T3, EC, M, D2>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent),
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> Power

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                    unit = U2(mass, distance) to time)

    operator fun times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: CapacitanceAmount<T, EC, M, D>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time)
                                      / convert(that.mass to mass,
                                                that.distance to distance,
                                                that.distance to distance),
                             unit = U2(electricCurrent, time))

    operator fun times(that: CapacitanceAmount<T3, EC, M, D2>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> MagneticFlux

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.time to time),
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun times(that: TimeAmount<T3>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion
}

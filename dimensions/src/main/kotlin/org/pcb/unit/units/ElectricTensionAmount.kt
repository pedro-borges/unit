package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.ElectricTension
import org.pcb.unit.dimension.Length
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
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class ElectricTensionAmount<M : Unit<Mass>, L2 : Unit<Length>, T3 : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<U2<M, L2>, U2<T3, EC>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.major.first to 1,
                                                 unit.major.second to 2,
                                                 unit.minor.first to -3,
                                                 unit.minor.second to -1)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricTension>) =
            ElectricTensionAmount(amount = amount.toBigBigDecimal() * unit.scale,
                                  unit = U2(KILOGRAM, METER) to U2(SECOND, AMPERE))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount * that.amount * that.unit.scale,
                              unit = U2(mass, length) to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricTensionAmount(amount = amount / that.amount / that.unit.scale,
                              unit = U2(mass, length) to U2(time, electricCurrent))

    @JvmName("plusElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount + that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun plus(that: ElectricTensionAmount<M, L2, T3, EC>) =
        ElectricTensionAmount(amount = amount + that.amount,
                              unit = U2(mass, length) to U2(time, electricCurrent))

    @JvmName("minusElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricTensionAmount(amount = amount - that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent),
                              unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun minus(that: ElectricTensionAmount<M, L2, T3, EC>) =
        ElectricTensionAmount(amount = amount - that.amount,
                              unit = U2(mass, length) to U2(time, electricCurrent))

    // endregion

    // region -> Natural

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time,
                                        that.electricCurrent to electricCurrent),
                     unit = NUMERIC)

    operator fun div(that: ElectricTensionAmount<M, L2, T3, EC>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Energy

    @JvmName("timesElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time,
                                                             that.electricCurrent to electricCurrent),
                     unit = U2(mass, length) to time)

    operator fun times(that: ElectricChargeAmount<EC, T3>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, length) to time)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    operator fun div(that: ElectricResistanceAmount<M, L2, T3, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    @JvmName("timesElectricConductivity")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> times(that: ElectricConductivityAmount<T, EC, M, L>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun times(that: ElectricConductivityAmount<T3, EC, M, L2>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent),
                                 unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun div(that: ElectricCurrentAmount<EC>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(mass, length) to U2(time, electricCurrent))

    // endregion

    // region -> Power

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount * convert(that.electricCurrent to electricCurrent),
                    unit = U2(mass, length) to time)

    operator fun times(that: ElectricCurrentAmount<EC>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(mass, length) to time)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesElectricCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, L : Unit<Length>> times(that: ElectricCapacitanceAmount<T, EC, M, L>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time)
                                      / convert(that.mass to mass,
                                                that.length to length,
                                                that.length to length),
                             unit = U2(electricCurrent, time))

    operator fun times(that: ElectricCapacitanceAmount<T3, EC, M, L2>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> MagneticFlux

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.time to time),
                           unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun times(that: TimeAmount<T3>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(mass, length) to U2(time, electricCurrent))

    // endregion
}

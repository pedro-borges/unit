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
import org.pcb.unit.dimensions.MagneticFlux
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MagneticFluxAmount<D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, D>, U2<T, EC>>)
    : Amount<MagneticFlux>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = MagneticFlux::class,
                               units = mapOf(unit.major.first to 1,
                                             unit.major.second to 2,
                                             unit.minor.first to -2,
                                             unit.minor.second to -1)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFlux>) =
            MagneticFluxAmount(amount = amount * unit.scale,
                               unit = U2(kilo(GRAM), METER) to U2(SECOND, AMPERE))
    }

    // region -> MagneticFlux

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("plusMagneticFlux")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: MagneticFluxAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount + that.amount
                                    * convert(that.mass to mass,
                                              that.distance to distance,
                                              that.distance to distance)
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.electricCurrent to electricCurrent),
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun plus(that: MagneticFluxAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount + that.amount,
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    @JvmName("minusMagneticFlux")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: MagneticFluxAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount - that.amount
                                    * convert(that.mass to mass,
                                              that.distance to distance,
                                              that.distance to distance)
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.electricCurrent to electricCurrent),
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun minus(that: MagneticFluxAmount<D, EC, M, T>) =
        MagneticFluxAmount(amount = amount - that.amount,
                           unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> Numeric

    @JvmName("divMagneticFlux")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<D, EC, M, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         * convert(that.time to time,
                                   that.time to time,
                                   that.electricCurrent to electricCurrent))

    operator fun div(that: MagneticFluxAmount<D, EC, M, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Time

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass,
                                      that.distance to distance,
                                      that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.electricCurrent to electricCurrent),
                   unit = time)

    operator fun div(that: ElectricTensionAmount<D, EC, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> ElectricTension

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun div(that: TimeAmount<T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.time to time,
                                                                         that.electricCurrent to electricCurrent),
                                 unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun div(that: ElectricChargeAmount<EC, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(mass, distance) to U2(time, that.electricCurrent))

    // endregion

    // region -> ElectricCharge

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.mass to mass,
                                                that.distance to distance,
                                                that.distance to distance)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time,
                                                that.electricCurrent to electricCurrent,
                                                that.electricCurrent to electricCurrent),
                             unit = U2(electricCurrent, time))

    operator fun div(that: ElectricResistanceAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent),
                     unit = U2(mass, distance) to time)

    operator fun times(that: ElectricCurrentAmount<EC>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, distance) to time)

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesEnergy")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: EnergyAmount<D, M, T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun times(that: EnergyAmount<D, M, T>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        MagneticFluxDensityAmount(amount = amount / that.amount / convert(that.distance to distance,
                                                                          that.distance to distance),
                                  unit = mass to U2(time, electricCurrent))

    operator fun div(that: AreaAmount<D>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(time, electricCurrent))

    // endregion

    // region -> Area

    @JvmName("divMagneticFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<EC, M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.electricCurrent to electricCurrent),
                   unit = distance)

    operator fun div(that: MagneticFluxDensityAmount<EC, M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Inductance

    @JvmName("divElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        InductanceAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent),
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    operator fun div(that: ElectricCurrentAmount<EC>) =
        InductanceAmount(amount = amount / that.amount,
                         unit = U2(mass, distance) to U2(time, electricCurrent))

    // endregion

    // region -> ElectricCurrent

    @JvmName("divInductance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: InductanceAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    operator fun div(that: InductanceAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    // endregion
}

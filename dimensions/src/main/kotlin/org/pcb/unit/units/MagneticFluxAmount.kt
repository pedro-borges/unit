package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.MagneticFlux
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

open class MagneticFluxAmount<M : Unit<Mass>, L2 : Unit<Length>, T2 : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<U2<M, L2>, U2<T2, EC>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.major.first to 1,
                                                 unit.major.second to 2,
                                                 unit.minor.first to -2,
                                                 unit.minor.second to -1)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFlux>) =
            MagneticFluxAmount(amount = amount.toBigBigDecimal() * unit.scale,
                               unit = U2(KILOGRAM, METER) to U2(SECOND, AMPERE))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount * that.amount * that.unit.scale,
                           unit = U2(mass, length) to U2(time, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount / that.amount / that.unit.scale,
                           unit = U2(mass, length) to U2(time, electricCurrent))

    @JvmName("plusMagneticFlux")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: MagneticFluxAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount + that.amount
                                    * convert(that.mass to mass,
                                              that.length to length,
                                              that.length to length)
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.electricCurrent to electricCurrent),
                           unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun plus(that: MagneticFluxAmount<M, L2, T2, EC>) =
        MagneticFluxAmount(amount = amount + that.amount,
                           unit = U2(mass, length) to U2(time, electricCurrent))

    @JvmName("minusMagneticFlux")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: MagneticFluxAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount - that.amount
                                    * convert(that.mass to mass,
                                              that.length to length,
                                              that.length to length)
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.electricCurrent to electricCurrent),
                           unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun minus(that: MagneticFluxAmount<M, L2, T2, EC>) =
        MagneticFluxAmount(amount = amount - that.amount,
                           unit = U2(mass, length) to U2(time, electricCurrent))

    // endregion

    // region -> Natural

    @JvmName("divMagneticFlux")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<M, L, T, EC>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.electricCurrent to electricCurrent),
                     unit = NUMERIC)

    operator fun div(that: MagneticFluxAmount<M, L2, T2, EC>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Time

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass,
                                      that.length to length,
                                      that.length to length)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time,
                                      that.electricCurrent to electricCurrent),
                   unit = time)

    operator fun div(that: ElectricTensionAmount<M, L2, T2, EC>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> ElectricTension

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun div(that: TimeAmount<T2>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(mass, length) to U2(time, electricCurrent))

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.time to time,
                                                                         that.electricCurrent to electricCurrent),
                                 unit = U2(mass, length) to U2(time, electricCurrent))

    operator fun div(that: ElectricChargeAmount<EC, T2>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(mass, length) to U2(time, that.electricCurrent))

    // endregion

    // region -> ElectricCharge

    @JvmName("divElectricResistance")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.mass to mass,
                                                that.length to length,
                                                that.length to length)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time,
                                                that.electricCurrent to electricCurrent,
                                                that.electricCurrent to electricCurrent),
                             unit = U2(electricCurrent, time))

    operator fun div(that: ElectricResistanceAmount<M, L2, T2, EC>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent),
                     unit = U2(mass, length) to time)

    operator fun times(that: ElectricCurrentAmount<EC>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, length) to time)

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesEnergy")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: EnergyAmount<M, L, T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun times(that: EnergyAmount<M, L2, T2>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divArea")
    operator fun <L : Unit<Length>> div(that: AreaAmount<L>) =
        MagneticFluxDensityAmount(amount = amount / that.amount / convert(that.length to length,
                                                                          that.length to length),
                                  unit = mass to U2(time, electricCurrent))

    operator fun div(that: AreaAmount<L2>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(time, electricCurrent))

    // endregion

    // region -> Area

    @JvmName("divMagneticFluxDensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<M, T, EC>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.electricCurrent to electricCurrent),
                   unit = length)

    operator fun div(that: MagneticFluxDensityAmount<M, T2, EC>) =
        AreaAmount(amount = amount / that.amount,
                   unit = length)

    // endregion
}

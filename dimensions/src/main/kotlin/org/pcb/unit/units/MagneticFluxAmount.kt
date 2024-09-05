package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Fraction
import org.pcb.unit.Triple
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

open class MagneticFluxAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Fraction<Triple<M, L, L>, Triple<T, T, EC>>)
    : Amount<ElectricCurrent>(amount = amount.toBigBigDecimal()
                                       * convert(unit.major.third to unit.major.second)
                                       / convert(unit.minor.second to unit.minor.first),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = listOf(unit.major.first to 1,
                                                 unit.major.second to 2,
                                                 unit.minor.first to -2,
                                                 unit.minor.third to -1)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor.first
    val electricCurrent = unit.minor.third

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFlux>) =
            MagneticFluxAmount(amount = amount.toBigBigDecimal() * unit.scale,
                               unit = (KILOGRAM * METER * METER) /
                                      (SECOND * SECOND * AMPERE))
    }

    // region -> ElectricTension

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount * that.amount * that.unit.scale,
                           unit = (mass * length * length) /
                                  (time * time * electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount / that.amount / that.unit.scale,
                           unit = (mass * length * length) /
                                  (time * time * electricCurrent))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> plus(that: MagneticFluxAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount + that.amount
                                    * convert(that.mass to mass,
                                              that.length to length,
                                              that.length to length)
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.electricCurrent to electricCurrent),
                           unit = (mass * length * length) /
                                  (time * time * electricCurrent))

    @JvmName("plusMagneticFlux")
    operator fun plus(that: MagneticFluxAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount + that.amount,
                           unit = (mass * length * length) /
                                  (time * time * electricCurrent))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> minus(that: MagneticFluxAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount - that.amount
                                    * convert(that.mass to mass,
                                              that.length to length,
                                              that.length to length)
                                    / convert(that.time to time,
                                              that.time to time,
                                              that.electricCurrent to electricCurrent),
                           unit = (mass * length * length) /
                                  (time * time * electricCurrent))

    @JvmName("minusMagneticFlux")
    operator fun minus(that: MagneticFluxAmount<M, L, T, EC>) =
        MagneticFluxAmount(amount = amount - that.amount,
                           unit = (mass * length * length) /
                                  (time * time * electricCurrent))

    // endregion

    // region -> Natural

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<M, L, T, EC>) =
        amount / that.amount /
        convert(that.mass to mass,
                that.length to length,
                that.length to length) *
        convert(that.time to time,
                that.time to time,
                that.electricCurrent to electricCurrent)

    @JvmName("divMagneticFlux")
    operator fun div(that: MagneticFluxAmount<M, L, T, EC>) =
        amount / that.amount

    // endregion

    // region -> Energy

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time,
                                                             that.electricCurrent to electricCurrent),
                     unit = (mass * length * length) /
                            (time * time))

    @JvmName("timesElectricCharge")
    operator fun times(that: ElectricChargeAmount<EC, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = (mass * length * length) /
                            (time * time))

    // endregion

    // region -> Time

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

    @JvmName("divElectricTension")
    operator fun div(that: ElectricTensionAmount<M, L, T, EC>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> ElectricTension

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              unit = (mass * length * length) /
                                     (time * time * time * electricCurrent))

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = (mass * length * length) /
                                     (time * time * time * electricCurrent))

    // endregion

    // region -> ElectricResistance

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricResistanceAmount(amount = amount / that.amount / convert(that.time to time,
                                                                         that.electricCurrent to electricCurrent),
                                 unit = (mass * length * length) /
                                        (time * time * time * electricCurrent * electricCurrent))

    @JvmName("divElectricCharge")
    operator fun div(that: ElectricChargeAmount<EC, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = (mass * length * length) /
                                        (time * time * time * electricCurrent * electricCurrent))

    // endregion

    // region -> ElectricCharge

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
                             unit = electricCurrent * time)

    @JvmName("divElectricResistance")
    operator fun div(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = electricCurrent * time)

    // endregion

    // region -> ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    @JvmName("timesElectricResistance")
    operator fun times(that: EnergyAmount<M, L, T>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> Energy

    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent),
                     unit = (mass * length * length) /
                            (time * time))

    @JvmName("timesElectricCurrent")
    operator fun times(that: ElectricCurrentAmount<EC>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = (mass * length * length) /
                            (time * time))

    // endregion

    // region -> ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> times(that: EnergyAmount<M, L, T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       / convert(that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    // endregion
}

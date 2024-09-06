package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Energy
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class EnergyAmount<M : Unit<Mass>, L2 : Unit<Length>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<org.pcb.unit.T2<M, L2>, T2>)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.major.first to 1,
                                       unit.major.second to 2,
                                       unit.minor to -2)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Energy>) =
            EnergyAmount(amount = amount.toBigBigDecimal() * unit.scale,
                         unit = T2(KILOGRAM, METER) to SECOND)
    }

    // region -> Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount * that.amount * that.unit.scale,
                     unit = T2(mass, length) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount / that.amount / that.unit.scale,
                     unit = T2(mass, length) to time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount + that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = T2(mass, length) to time)

    @JvmName("plusEnergy")
    operator fun plus(that: EnergyAmount<M, L2, T2>) =
        EnergyAmount(amount = amount + that.amount,
                     unit = T2(mass, length) to time)


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount - that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = T2(mass, length) to time)

    @JvmName("minusEnergy")
    operator fun minus(that: EnergyAmount<M, L2, T2>) =
        EnergyAmount(amount = amount - that.amount,
                     unit = T2(mass, length) to time)

    // endregion

    // region -> Natural

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: EnergyAmount<M, L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    @JvmName("divEnergy")
    operator fun div(that: EnergyAmount<M, L2, T2>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Force

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        ForceAmount(amount = amount / that.amount / convert(that.length to length),
                    unit = T2(mass, length) to time)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L2>) =
        ForceAmount(amount = amount / that.amount,
                    unit = T2(mass, length) to time)

    // endregion

    // region -> Length

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = length)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, L2, T2>) =
        LengthAmount(amount = amount / that.amount,
                     unit = length)

    // endregion

    // region -> Time

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: PowerAmount<M, L, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass,
                                      that.length to length)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    @JvmName("divPower")
    operator fun div(that: PowerAmount<M, L2, T2>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Speed

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: MomentumAmount<M, L, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass,
                                       that.length to length)
                             * convert(that.time to time),
                    unit = length to time)

    @JvmName("divMomentum")
    operator fun div(that: MomentumAmount<M, L2, T2>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = length to time)

    // endregion

    // region -> Momentum

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.length to length)
                                * convert(that.time to time),
                       unit = T2(mass, length) to time)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L2, T2>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = T2(mass, length) to time)

    // endregion

    // region -> Power

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount / convert(that.time to time),
                    unit = T2(mass, length) to time)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T2>) =
        PowerAmount(amount = amount / that.amount,
                    unit = T2(mass, length) to time)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              unit = T2(mass, length) to T2(time, that.electricCurrent))

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<EC, T2>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = T2(mass, length) to T2(time, that.electricCurrent))

    // endregion

    // region -> ElectricCharge

    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.mass to mass,
                                                that.length to length,
                                                that.length to length)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = T2(that.electricCurrent, time))

    @JvmName("divElectricTension")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L2, T2, EC>) =
        ElectricChargeAmount(amount = amount / that.amount / convert(that.time to time),
                             unit = T2(that.electricCurrent, time))

    // endregion

    // region -> MagneticFlux

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = T2(mass, length) to T2(time, that.electricCurrent))

    // endregion

    // region -> ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: MagneticFluxAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       * convert(that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    @JvmName("divMagneticFlux")
    operator fun <EC : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<M, L2, T2, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion
}
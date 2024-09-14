package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Energy
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class EnergyAmount<M : Unit<Mass>, D2 : Unit<Distance>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, D2>, T2>)
    : Amount<Energy>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Energy::class,
                         units = mapOf(unit.major.first to 1,
                                       unit.major.second to 2,
                                       unit.minor to -2)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Energy>) =
            EnergyAmount(amount = amount * unit.scale,
                         unit = U2(kilo(GRAM), METER) to SECOND)
    }

    // region -> Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = U2(mass, distance) to time)

    @JvmName("plusEnergy")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> plus(that: EnergyAmount<M, D, T>) =
        EnergyAmount(amount = amount + that.amount
                              * convert(that.mass to mass,
                                        that.distance to distance,
                                        that.distance to distance)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = U2(mass, distance) to time)

    operator fun plus(that: EnergyAmount<M, D2, T2>) =
        EnergyAmount(amount = amount + that.amount,
                     unit = U2(mass, distance) to time)

    @JvmName("minusEnergy")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> minus(that: EnergyAmount<M, D, T>) =
        EnergyAmount(amount = amount - that.amount
                              * convert(that.mass to mass,
                                        that.distance to distance,
                                        that.distance to distance)
                              / convert(that.time to time,
                                        that.time to time),
                     unit = U2(mass, distance) to time)

    operator fun minus(that: EnergyAmount<M, D2, T2>) =
        EnergyAmount(amount = amount - that.amount,
                     unit = U2(mass, distance) to time)

    // endregion

    // region -> Numeric

    @JvmName("divEnergy")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: EnergyAmount<M, D, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         * convert(that.time to time,
                                   that.time to time))

    operator fun div(that: EnergyAmount<M, D2, T2>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Force

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        ForceAmount(amount = amount / that.amount / convert(that.distance to distance),
                    unit = U2(mass, distance) to time)

    operator fun div(that: DistanceAmount<D2>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Distance

    @JvmName("divForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: ForceAmount<M, D, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass,
                                          that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: ForceAmount<M, D2, T2>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Time

    @JvmName("divPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: PowerAmount<M, D, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass,
                                      that.distance to distance,
                                      that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: PowerAmount<M, D2, T2>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Speed

    @JvmName("divMomentum")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: MomentumAmount<M, D, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass,
                                       that.distance to distance)
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun div(that: MomentumAmount<M, D2, T2>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Momentum

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.distance to distance)
                                * convert(that.time to time),
                       unit = U2(mass, distance) to time)

    operator fun div(that: SpeedAmount<D2, T2>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(mass, distance) to time)

    // endregion

    // region -> Power

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount / convert(that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun div(that: TimeAmount<T2>) =
        PowerAmount(amount = amount / that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> ElectricTension

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              unit = U2(mass, distance) to U2(time, that.electricCurrent))

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<EC, T2>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(mass, distance) to U2(time, that.electricCurrent))

    // endregion

    // region -> ElectricCharge

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.mass to mass,
                                                that.distance to distance,
                                                that.distance to distance)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = U2(that.electricCurrent, time))

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, D2, T2, EC>) =
        ElectricChargeAmount(amount = amount / that.amount / convert(that.time to time),
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> MagneticFlux

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(mass, distance) to U2(time, that.electricCurrent))

    // endregion

    // region -> ElectricCurrent

    @JvmName("divMagneticFlux")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: MagneticFluxAmount<M, D, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       * convert(that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<M, D2, T2, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> Pressure

    @JvmName("divVolume")
    operator fun <D : Unit<Distance>> div(that: VolumeAmount<D>) =
        PressureAmount(amount = amount / that.amount / convert(that.distance to distance,
                                                               that.distance to distance,
                                                               that.distance to distance),
                       unit = mass to U2(distance, time))

    operator fun div(that: VolumeAmount<D2>) =
        PressureAmount(amount = amount / that.amount,
                       unit = mass to U2(distance, time))

    // endregion

    // region -> Volume

    @JvmName("divPressure")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: PressureAmount<M, D, T>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time),
                     unit = distance)

    operator fun div(that: PressureAmount<M, D2, T2>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion
}
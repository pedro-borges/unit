package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Action
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

open class ActionAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, T>)
    : Amount<Action>(amount = amount.toBigDecimal(),
                     unit = CompositeUnit(
                         domain = Action::class,
                         units = mapOf(unit.major.first to 2,
                                       unit.major.second to 1,
                                       unit.minor to -1)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Action>) =
            ActionAmount(amount = amount * unit.scale,
                         unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Action

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ActionAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                     unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ActionAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                     unit = U2(distance, mass) to time)

    @JvmName("plusAction")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: ActionAmount<D, M, T>) =
        ActionAmount(amount = amount + that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun plus(that: ActionAmount<D, M, T>) =
        ActionAmount(amount = amount + that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("minusAction")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: ActionAmount<D, M, T>) =
        ActionAmount(amount = amount - that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun minus(that: ActionAmount<D, M, T>) =
        ActionAmount(amount = amount - that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divAction")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ActionAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ActionAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time),
                   unit = distance)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Distance

    @JvmName("divMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<D, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.time to time),
                       unit = distance)

    operator fun div(that: MomentumAmount<D, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("divMagneticFlux")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = U2(that.electricCurrent, time))

    operator fun <EC : Unit<ElectricCurrent>> div(that: MagneticFluxAmount<D, EC, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: FrequencyAmount<T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        EnergyAmount(amount = amount / that.amount
                              / convert(that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun div(that: TimeAmount<T>) =
        EnergyAmount(amount = amount / that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divMomentOfInertia")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: MomentOfInertiaAmount<D, M>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass),
                        unit = NUMERIC to time)

    operator fun div(that: MomentOfInertiaAmount<D, M>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MagneticFlux

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxAmount(amount = amount / that.amount
                                    / convert(that.time to time),
                           unit = U2(distance, mass) to U2(that.electricCurrent, time))

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(distance, mass) to U2(that.electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = mass to time)

    operator fun div(that: AreaAmount<D>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    @JvmName("timesFuelEfficiency")
    operator fun <D : Unit<Distance>> times(that: FuelEfficiencyAmount<D>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = mass to time)

    operator fun times(that: FuelEfficiencyAmount<D>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> MomentOfInertia

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MomentOfInertiaAmount(amount = amount / that.amount
                                       * convert(that.time to time),
                              unit = U2(distance, mass))

    operator fun div(that: FrequencyAmount<T>) =
        MomentOfInertiaAmount(amount = amount / that.amount,
                              unit = U2(distance, mass))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentOfInertiaAmount(amount = amount * that.amount
                                       * convert(that.time to time),
                              unit = U2(distance, mass))

    operator fun times(that: TimeAmount<T>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(distance, mass))

    // endregion

    // region -> Momentum

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.distance to distance),
                       unit = U2(distance, mass) to time)

    operator fun div(that: DistanceAmount<D>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("timesSpacialFrequency")
    operator fun <D : Unit<Distance>> times(that: SpacialFrequencyAmount<D>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.distance to distance),
                       unit = U2(distance, mass) to time)

    operator fun times(that: SpacialFrequencyAmount<D>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Time

    @JvmName("divEnergy")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: EnergyAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}

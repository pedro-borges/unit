package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal
import org.pcb.unit.units.base.Numeric
import org.pcb.unit.units.base.Numeric.NUMERIC

open class ElectricCurrentAmount<EC : Unit<ElectricCurrent>>(
        amount: Number,
        unit: EC)
    : Amount<ElectricCurrent>(amount = amount.toBigDecimal(),
                              unit = CompositeUnit(
                                  domain = ElectricCurrent::class,
                                  units = mapOf(unit to 1)))
{
    val electricCurrent = unit

    // region -> ElectricCurrent

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                              unit = electricCurrent)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricCurrentAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                              unit = electricCurrent)

    @JvmName("plusElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount * convert(that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    operator fun plus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount + that.amount,
                              unit = electricCurrent)

    @JvmName("minusElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount * convert(that.electricCurrent to electricCurrent),
                              unit = electricCurrent)

    operator fun minus(that: ElectricCurrentAmount<EC>) =
        ElectricCurrentAmount(amount = amount - that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> Natural

    @JvmName("divElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        Numeric(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent))

    operator fun div(that: ElectricCurrentAmount<EC>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> ElectricCharge

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, that.time))

    // endregion

    // region -> ElectricTension

    @JvmName("timesElectricResistance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent),
                              unit = U2(that.mass, that.distance) to U2(that.time, electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: ElectricResistanceAmount<M, D, T, EC>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(that.mass, that.distance) to U2(that.time, electricCurrent))

    @JvmName("divElectricConductance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricConductanceAmount<T, EC, M, D>) =
        ElectricTensionAmount(amount = amount / that.amount * convert(that.electricCurrent to electricCurrent,
                                                                      that.electricCurrent to electricCurrent),
                              unit = U2(that.mass, that.distance) to U2(that.time, electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: ElectricConductanceAmount<T, EC, M, D>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(that.mass, that.distance) to U2(that.time, electricCurrent))

    // endregion

    // region -> ElectricConductance

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricConductanceAmount(amount = amount / that.amount * convert(that.electricCurrent to electricCurrent),
                                  unit = U2(that.time, electricCurrent) to U2(that.mass, that.distance))

    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(that.time, electricCurrent) to U2(that.mass, that.distance))

    // endregion

    // region -> Power

    @JvmName("timesElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        PowerAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent),
                    unit = U2(that.mass, that.distance) to that.time)

    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: ElectricTensionAmount<M, D, T, EC>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(that.mass, that.distance) to that.time)

    // endregion

    // region -> Energy

    @JvmName("timesMagneticFlux")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: MagneticFluxAmount<M, D, T, EC>) =
        EnergyAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent),
                     unit = U2(that.mass, that.distance) to that.time)

    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: MagneticFluxAmount<M, D, T, EC>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.mass, that.distance) to that.time)

    // endregion

    // region -> MagneticFlux

    @JvmName("timesInductance")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> times(that: InductanceAmount<M, D, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount / convert(that.electricCurrent to electricCurrent,
                                                                   that.electricCurrent to electricCurrent),
                           unit = U2(that.mass, that.distance) to U2(that.time, electricCurrent))

    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> times(that: InductanceAmount<M, D, T, EC>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.mass, that.distance) to U2(that.time, electricCurrent))

    // endregion
}

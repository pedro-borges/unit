package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCharge
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class ElectricChargeAmount<EC : Unit<ElectricCurrent>, T : Unit<Time>>(
        amount: Number,
        val electricCurrent: EC,
        val time: T)
    : Amount<ElectricCharge>(amount = amount.toBigBigDecimal(),
                             unit = CompositeUnit(
                                 domain = ElectricCharge::class,
                                 units = listOf(electricCurrent to 1,
                                                time to 1)))
{
    companion object
    {
        operator fun invoke(amount: Number, electricCharge: Unit<ElectricCharge>) =
            ElectricChargeAmount(amount = amount.toBigBigDecimal() * electricCharge.scale,
                                 electricCurrent = AMPERE,
                                 time = SECOND)
    }

    // region ElectricCharge

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricChargeAmount(amount = amount * that.amount * that.unit.scale,
                             electricCurrent = electricCurrent,
                             time = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricChargeAmount(amount = amount / that.amount / that.unit.scale,
                             electricCurrent = electricCurrent,
                             time = time)

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> plus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount + that.amount * convert(that.electricCurrent to electricCurrent,
                                                                     that.time to time),
                             electricCurrent = electricCurrent,
                             time = time)

    @JvmName("plusElectricCharge")
    operator fun plus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount + that.amount,
                             electricCurrent = electricCurrent,
                             time = time)

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> minus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount - that.amount * convert(that.electricCurrent to electricCurrent,
                                                                     that.time to time),
                             electricCurrent = electricCurrent,
                             time = time)

    @JvmName("minusElectricCharge")
    operator fun minus(that: ElectricChargeAmount<EC, T>) =
        ElectricChargeAmount(amount = amount - that.amount,
                             electricCurrent = electricCurrent,
                             time = time)

    // endregion

    // region Scalar

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.electricCurrent to electricCurrent,
                                                              that.time to time))

    @JvmName("divElectricCharge")
    operator fun div(that: ElectricChargeAmount<EC, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region ElectricCurrent

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricCurrentAmount(amount = amount / that.amount / that.unit.scale,
                              electricCurrent = electricCurrent)

    // endregion
}

package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.cannon.Ampere.AMPERE
import org.pcb.units.amount.cannon.Second.SECOND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.div
import org.pcb.units.domain.ElectricCharge
import org.pcb.units.domain.ElectricCurrent
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

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

    // region Time

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

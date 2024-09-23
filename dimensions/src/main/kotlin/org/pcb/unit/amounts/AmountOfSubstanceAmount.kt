package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AmountOfSubstance
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AmountOfSubstanceAmount<N : Unit<AmountOfSubstance>>(
        amount: Number,
        unit: N)
    : Amount<AmountOfSubstance>(amount = amount.toBigDecimal(),
                                unit = CompositeUnit(
                                    domain = AmountOfSubstance::class,
                                    units = mapOf(unit to 1)))
{
    val amountOfSubstance = unit

    // region -> AmountOfSubstance

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AmountOfSubstanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                unit = amountOfSubstance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AmountOfSubstanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                unit = amountOfSubstance)

    @JvmName("plusAmountOfSubstance")
    operator fun <N : Unit<AmountOfSubstance>> plus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount + that.amount
                                         * convert(that.amountOfSubstance to amountOfSubstance),
                                unit = amountOfSubstance)

    operator fun plus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount + that.amount,
                                unit = amountOfSubstance)

    @JvmName("minusAmountOfSubstance")
    operator fun <N : Unit<AmountOfSubstance>> minus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount - that.amount
                                         * convert(that.amountOfSubstance to amountOfSubstance),
                                unit = amountOfSubstance)

    operator fun minus(that: AmountOfSubstanceAmount<N>) =
        AmountOfSubstanceAmount(amount = amount - that.amount,
                                unit = amountOfSubstance)

    // endregion

    // region -> Scalar

    @JvmName("divAmountOfSubstance")
    operator fun <N : Unit<AmountOfSubstance>> div(that: AmountOfSubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.amountOfSubstance to amountOfSubstance),
                     unit = NUMERIC)

    operator fun div(that: AmountOfSubstanceAmount<N>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion
}

package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Debt : Dimension
{
    override val composite = mapOf(Currency to 1,
                                   Time to 1)

    operator fun div(that: Scalar) = Debt
    operator fun times(that: Scalar) = Debt

    operator fun div(that: Debt) = Scalar

    operator fun div(that: Time) = Currency
    operator fun times(that: Frequency) = Currency

    operator fun div(that: Currency) = Time
}

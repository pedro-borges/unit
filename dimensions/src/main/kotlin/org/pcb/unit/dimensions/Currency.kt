package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Currency : Dimension
{
    override val composite = mapOf(Currency to 1)

    operator fun div(that: Scalar) = Currency
    operator fun times(that: Scalar) = Currency

    operator fun div(that: Currency) = Scalar

    operator fun div(that: Frequency) = Debt
    operator fun times(that: Time) = Debt

    operator fun div(that: Debt) = Frequency

    operator fun div(that: Time) = Rent
    operator fun times(that: Frequency) = Rent

    operator fun div(that: Rent) = Time
}

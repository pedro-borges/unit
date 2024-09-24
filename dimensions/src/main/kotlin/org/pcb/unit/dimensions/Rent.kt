package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Rent : Dimension
{
    override val composite = mapOf(Currency to 1,
                                   Time to -1)

    operator fun div(that: Scalar) = Rent
    operator fun times(that: Scalar) = Rent

    operator fun div(that: Rent) = Scalar

    operator fun div(that: Frequency) = Currency
    operator fun times(that: Time) = Currency

    operator fun div(that: Currency) = Frequency
}

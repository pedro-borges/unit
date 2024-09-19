package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(currency = 1)
object Currency : Dimension
{
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

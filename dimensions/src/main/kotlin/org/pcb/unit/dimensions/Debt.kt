package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(currency = 1,
           time = 1)
object Debt : Dimension
{
    operator fun div(that: Scalar) = Debt
    operator fun times(that: Scalar) = Debt

    operator fun div(that: Debt) = Scalar

    operator fun div(that: Time) = Currency
    operator fun times(that: Frequency) = Currency

    operator fun div(that: Currency) = Time
}

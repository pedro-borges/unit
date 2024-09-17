package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(currency = 1,
           time = -1)
object Rent : Dimension
{
    operator fun div(that: Scalar) = Rent
    operator fun times(that: Scalar) = Rent

    operator fun div(that: Rent) = Scalar

    operator fun div(that: Frequency) = Currency
    operator fun times(that: Time) = Currency

    operator fun div(that: Currency) = Frequency
}
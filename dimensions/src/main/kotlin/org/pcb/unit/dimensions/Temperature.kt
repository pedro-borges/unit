package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(temperature = 1)
object Temperature : Dimension
{
    operator fun div(that: Scalar) = Temperature
    operator fun times(that: Scalar) = Temperature

    operator fun div(that: Temperature) = Scalar
}

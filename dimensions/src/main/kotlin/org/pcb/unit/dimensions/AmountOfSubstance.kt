package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite()
object AmountOfSubstance : Dimension
{
    operator fun div(that: Scalar) = AmountOfSubstance
    operator fun times(that: Scalar) = AmountOfSubstance

    operator fun div(that: AmountOfSubstance) = Scalar
}

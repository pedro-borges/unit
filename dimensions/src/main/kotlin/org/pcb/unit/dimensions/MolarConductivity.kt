package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 3,
           electricCurrent = 2,
           amountOfSubstance = -1,
           mass = -1)
object MolarConductivity : Dimension
{
    operator fun div(that: Scalar) = MolarConductivity
    operator fun times(that: Scalar) = MolarConductivity

    operator fun div(that: MolarConductivity) = Scalar
}

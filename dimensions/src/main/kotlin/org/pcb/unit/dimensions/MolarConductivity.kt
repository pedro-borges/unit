package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 3,
           electricCurrent = 2,
           mass = -1,
           substance = -1)
object MolarConductivity : Dimension
{
    operator fun div(that: Scalar) = MolarConductivity
    operator fun times(that: Scalar) = MolarConductivity

    operator fun div(that: MolarConductivity) = Scalar

    operator fun div(that: Permittivity) = CatalyticEfficiency

    operator fun div(that: CatalyticEfficiency) = Permittivity
}

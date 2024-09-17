package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           electricCurrent = -1,
           time = -2)
object MagneticFluxDensity : Dimension
{
    operator fun div(that: Scalar) = MagneticFluxDensity
    operator fun times(that: Scalar) = MagneticFluxDensity

    operator fun div(that: MagneticFluxDensity) = Scalar

    operator fun times(that: Area) = MagneticFlux

    operator fun times(that: ElectricCharge) = MassFlowRate
}
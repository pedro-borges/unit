package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           time = -1)
object MassFlowRate : Dimension
{
    operator fun div(that: Scalar) = MassFlowRate
    operator fun times(that: Scalar) = MassFlowRate

    operator fun div(that: MassFlowRate) = Scalar

    operator fun div(that: MagneticFluxDensity) = ElectricCharge

    operator fun div(that: Pace) = Force
    operator fun times(that: Speed) = Force

    operator fun div(that: Mass) = Frequency

    operator fun div(that: Frequency) = Mass
    operator fun times(that: Time) = Mass

    operator fun div(that: ElectricCharge) = MagneticFluxDensity

    operator fun times(that: Distance) = Momentum

    operator fun div(that: Force) = Pace
}
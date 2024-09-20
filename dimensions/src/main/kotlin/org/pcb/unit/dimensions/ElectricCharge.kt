package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(electricCurrent = 1,
           time = 1)
object ElectricCharge : Dimension
{
    operator fun div(that: Scalar) = ElectricCharge
    operator fun times(that: Scalar) = ElectricCharge

    operator fun div(that: ElectricCharge) = Scalar

    operator fun times(that: MagneticFlux) = Action

    operator fun div(that: ElectricTension) = Capacitance

    operator fun div(that: MagneticFlux) = ElectricConductance

    operator fun div(that: Time) = ElectricCurrent
    operator fun times(that: Frequency) = ElectricCurrent

    operator fun div(that: Capacitance) = ElectricTension

    operator fun times(that: ElectricTension) = Energy

    operator fun div(that: ElectricConductance) = MagneticFlux
    operator fun times(that: ElectricResistance) = MagneticFlux

    operator fun times(that: MagneticFluxDensity) = MassFlowRate

    operator fun div(that: ElectricCurrent) = Time
}

package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(electricCurrent = 1)
object ElectricCurrent : Dimension
{
    operator fun div(that: Scalar) = ElectricCurrent
    operator fun times(that: Scalar) = ElectricCurrent

    operator fun div(that: ElectricCurrent) = Scalar

    operator fun div(that: Frequency) = ElectricCharge
    operator fun times(that: Time) = ElectricCharge

    operator fun div(that: ElectricTension) = ElectricConductance

    operator fun div(that: ElectricConductance) = ElectricTension
    operator fun times(that: ElectricResistance) = ElectricTension

    operator fun times(that: MagneticFlux) = Energy

    operator fun div(that: ElectricCharge) = Frequency

    operator fun times(that: Inductance) = MagneticFlux

    operator fun times(that: ElectricTension) = Power

    operator fun times(that: MagneticFluxDensity) = RadiantExposure
}

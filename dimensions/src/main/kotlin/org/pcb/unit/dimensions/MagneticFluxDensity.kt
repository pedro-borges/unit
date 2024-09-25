package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MagneticFluxDensity : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   ElectricCurrent to -1,
                                   Time to -2)

    operator fun div(that: Scalar) = MagneticFluxDensity
    operator fun times(that: Scalar) = MagneticFluxDensity

    operator fun div(that: MagneticFluxDensity) = Scalar

    operator fun div(that: Inductance) = ElectricCurrentDensity

    operator fun div(that: MagneticFlux) = FuelEfficiency

    operator fun div(that: ElectricCurrentDensity) = Inductance

    operator fun div(that: FuelEfficiency) = MagneticFlux
    operator fun times(that: Area) = MagneticFlux

    operator fun times(that: ElectricCharge) = MassFlowRate

    operator fun times(that: ElectricCurrent) = RadiantExposure
}

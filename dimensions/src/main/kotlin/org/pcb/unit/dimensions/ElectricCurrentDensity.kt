package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricCurrentDensity : Dimension
{
    override val composite = mapOf(ElectricCurrent to 1,
                                   Distance to -2)

    operator fun div(that: Scalar) = ElectricCurrentDensity
    operator fun times(that: Scalar) = ElectricCurrentDensity

    operator fun div(that: ElectricCurrentDensity) = Scalar

    operator fun div(that: FuelEfficiency) = ElectricCurrent
    operator fun times(that: Area) = ElectricCurrent

    operator fun div(that: ElectricCurrent) = FuelEfficiency

    operator fun times(that: ElectricTension) = Intensity

    operator fun times(that: Inductance) = MagneticFluxDensity

    operator fun times(that: MagneticFlux) = RadiantExposure
}

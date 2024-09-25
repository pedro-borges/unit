package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object SpecificEnergy : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Time to -2)

    operator fun div(that: Scalar) = SpecificEnergy
    operator fun times(that: Scalar) = SpecificEnergy

    operator fun div(that: SpecificEnergy) = Scalar

    operator fun div(that: Jerk) = Absement

    operator fun div(that: Distance) = Acceleration
    operator fun times(that: SpacialFrequency) = Acceleration

    operator fun div(that: Acceleration) = Distance

    operator fun times(that: Mass) = Energy

    operator fun div(that: SpecificVolume) = EnergyDensity
    operator fun times(that: MassDensity) = EnergyDensity

    operator fun times(that: LinearDensity) = Force

    operator fun div(that: Absement) = Jerk

    operator fun div(that: MolarEnergy) = Molality

    operator fun div(that: Molality) = MolarEnergy
    operator fun times(that: MolarMass) = MolarEnergy

    operator fun times(that: MassFlowRate) = Power

    operator fun times(that: AreaDensity) = RadiantExposure

    operator fun div(that: Temperature) = SpecificHeatCapacity
    operator fun times(that: ThermalExpansionCoefficient) = SpecificHeatCapacity

    operator fun div(that: EnergyDensity) = SpecificVolume

    operator fun div(that: Speed) = Speed
    operator fun times(that: Pace) = Speed

    operator fun div(that: SpecificHeatCapacity) = Temperature

    operator fun times(that: Absement) = VolumetricFlow
}

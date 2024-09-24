package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MassDensity : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Distance to -3)

    operator fun div(that: Scalar) = MassDensity
    operator fun times(that: Scalar) = MassDensity

    operator fun div(that: MassDensity) = Scalar
    operator fun times(that: SpecificVolume) = Scalar

    operator fun div(that: SpacialFrequency) = AreaDensity
    operator fun times(that: Distance) = AreaDensity

    operator fun times(that: SpecificEnergy) = EnergyDensity

    operator fun div(that: LinearDensity) = FuelEfficiency

    operator fun div(that: FuelEfficiency) = LinearDensity
    operator fun times(that: Area) = LinearDensity

    operator fun times(that: Volume) = Mass

    operator fun times(that: VolumetricFlow) = MassFlowRate

    operator fun div(that: Molarity) = MolarMass
    operator fun times(that: MolarVolume) = MolarMass

    operator fun div(that: MolarMass) = Molarity
    operator fun times(that: Molality) = Molarity

    operator fun div(that: AreaDensity) = SpacialFrequency
}

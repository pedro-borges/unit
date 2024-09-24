package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object SpecificVolume : Dimension
{
    override val composite = mapOf(Distance to 3,
                                   Mass to -1)

    operator fun div(that: Scalar) = SpecificVolume
    operator fun times(that: Scalar) = SpecificVolume

    operator fun div(that: SpecificVolume) = Scalar
    operator fun times(that: MassDensity) = Scalar

    operator fun times(that: LinearDensity) = Area

    operator fun times(that: AreaDensity) = Distance

    operator fun div(that: MolarVolume) = Molality
    operator fun times(that: Molarity) = Molality

    operator fun div(that: Molality) = MolarVolume
    operator fun times(that: MolarMass) = MolarVolume

    operator fun times(that: EnergyDensity) = SpecificEnergy

    operator fun times(that: Mass) = Volume

    operator fun times(that: MassFlowRate) = VolumetricFlow
}

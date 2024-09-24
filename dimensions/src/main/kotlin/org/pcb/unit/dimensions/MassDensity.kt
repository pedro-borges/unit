package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           distance = -3)
object MassDensity : Dimension
{
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

package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 3,
           mass = -1)
object SpecificVolume : Dimension
{
    operator fun div(that: Scalar) = SpecificVolume
    operator fun times(that: Scalar) = SpecificVolume

    operator fun div(that: SpecificVolume) = Scalar
    operator fun times(that: MassDensity) = Scalar

    operator fun times(that: AreaDensity) = Distance

    operator fun times(that: EnergyDensity) = SpecificEnergy

    operator fun times(that: Mass) = Volume

    operator fun times(that: MassFlowRate) = VolumetricFlow
}

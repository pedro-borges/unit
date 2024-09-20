package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           distance = -1,
           time = -2)
object EnergyDensity : Dimension
{
    operator fun div(that: Scalar) = EnergyDensity
    operator fun times(that: Scalar) = EnergyDensity

    operator fun div(that: EnergyDensity) = Scalar

    operator fun times(that: Volume) = Energy

    operator fun times(that: Area) = Force

    operator fun times(that: VolumetricFlow) = Power
}

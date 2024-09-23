package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 3)
object Volume : Dimension
{
    operator fun div(that: Scalar) = Volume
    operator fun times(that: Scalar) = Volume

    operator fun div(that: Volume) = Scalar

    operator fun div(that: Distance) = Area
    operator fun times(that: SpacialFrequency) = Area

    operator fun div(that: Area) = Distance
    operator fun times(that: FuelEfficiency) = Distance

    operator fun times(that: EnergyDensity) = Energy

    operator fun div(that: SpecificVolume) = Mass
    operator fun times(that: MassDensity) = Mass

    operator fun div(that: Substance) = MolarVolume

    operator fun times(that: PowerDensity) = Power

    operator fun div(that: Mass) = SpecificVolume

    operator fun div(that: MolarVolume) = Substance
    operator fun times(that: Molarity) = Substance

    operator fun div(that: ThermalResistance) = ThermalConductivity

    operator fun div(that: ThermalConductivity) = ThermalResistance

    operator fun div(that: VolumetricFlow) = Time

    operator fun div(that: Time) = VolumetricFlow
    operator fun times(that: Frequency) = VolumetricFlow
}

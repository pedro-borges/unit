package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Volume : Dimension
{
    override val composite = mapOf(Distance to 3)

    operator fun div(that: Scalar) = Volume
    operator fun times(that: Scalar) = Volume

    operator fun div(that: Volume) = Scalar

    operator fun times(that: Viscosity) = Action

    operator fun div(that: Distance) = Area
    operator fun times(that: SpacialFrequency) = Area

    operator fun div(that: Area) = Distance
    operator fun times(that: FuelEfficiency) = Distance

    operator fun times(that: ElectricDisplacementField) = ElectricDipoleMoment

    operator fun times(that: EnergyDensity) = Energy

    operator fun times(that: EnergyDensityGradient) = Force

    operator fun times(that: MagneticFieldStrength) = MagneticDipoleMoment

    operator fun div(that: SpecificVolume) = Mass
    operator fun times(that: MassDensity) = Mass

    operator fun div(that: Substance) = MolarVolume

    operator fun times(that: LinearDensity) = MomentOfInertia

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

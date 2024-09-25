package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Pace : Dimension
{
    override val composite = mapOf(Time to 1,
                                   Distance to -1)

    operator fun div(that: Scalar) = Pace
    operator fun times(that: Scalar) = Pace

    operator fun div(that: Pace) = Scalar
    operator fun times(that: Speed) = Scalar

    operator fun div(that: FuelEfficiency) = Absement
    operator fun times(that: Area) = Absement

    operator fun times(that: VolumetricFlow) = Area

    operator fun div(that: Permeability) = ElectricConductance

    operator fun div(that: Inductance) = ElectricConductivity

    operator fun div(that: Permittivity) = ElectricResistance

    operator fun times(that: Intensity) = EnergyDensity

    operator fun times(that: Power) = Force

    operator fun times(that: Acceleration) = Frequency

    operator fun div(that: Absement) = FuelEfficiency

    operator fun div(that: ElectricConductivity) = Inductance

    operator fun times(that: MassFlowRate) = LinearDensity

    operator fun times(that: Momentum) = Mass

    operator fun times(that: Force) = MassFlowRate

    operator fun times(that: Energy) = Momentum

    operator fun div(that: ElectricConductance) = Permeability
    operator fun times(that: ElectricResistance) = Permeability

    operator fun div(that: ElectricResistance) = Permittivity
    operator fun times(that: ElectricConductance) = Permittivity

    operator fun times(that: Yank) = RadiantExposure

    operator fun div(that: Time) = SpacialFrequency
    operator fun times(that: Frequency) = SpacialFrequency

    operator fun times(that: SpecificEnergy) = Speed

    operator fun div(that: SpacialFrequency) = Time
    operator fun times(that: Distance) = Time
}

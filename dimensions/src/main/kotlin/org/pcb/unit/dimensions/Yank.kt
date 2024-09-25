package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Yank : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Mass to 1,
                                   Time to -3)

    operator fun div(that: Scalar) = Yank
    operator fun times(that: Scalar) = Yank

    operator fun div(that: Yank) = Scalar

    operator fun div(that: MassFlowRate) = Acceleration

    operator fun div(that: PowerDensity) = Area

    operator fun div(that: Intensity) = Distance

    operator fun times(that: Absement) = Energy

    operator fun div(that: Frequency) = Force
    operator fun times(that: Time) = Force

    operator fun div(that: Force) = Frequency

    operator fun div(that: Distance) = Intensity
    operator fun times(that: SpacialFrequency) = Intensity

    operator fun div(that: Mass) = Jerk

    operator fun div(that: Jerk) = Mass

    operator fun div(that: Acceleration) = MassFlowRate

    operator fun div(that: SpacialFrequency) = Power
    operator fun times(that: Distance) = Power

    operator fun div(that: Area) = PowerDensity
    operator fun times(that: FuelEfficiency) = PowerDensity

    operator fun div(that: Speed) = RadiantExposure
    operator fun times(that: Pace) = RadiantExposure

    operator fun div(that: SpectralIntensity) = SolidAngle

    operator fun div(that: Power) = SpacialFrequency

    operator fun div(that: SolidAngle) = SpectralIntensity

    operator fun div(that: RadiantExposure) = Speed

    operator fun div(that: ThermalConductivity) = Temperature

    operator fun div(that: Temperature) = ThermalConductivity
    operator fun times(that: ThermalExpansionCoefficient) = ThermalConductivity
}

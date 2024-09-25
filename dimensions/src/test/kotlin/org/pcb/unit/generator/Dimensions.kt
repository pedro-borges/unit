package org.pcb.unit.generator

import org.pcb.unit.Composite

object Dimensions
{
    class Definition(val name: String,
                     val composite: Composite)

    val DIMENSIONS = listOf(
        Definition("Absement", Composite(distance = 1, time = 1)),
        Definition("Acceleration", Composite(distance = 1, time = -2)),
        Definition("Action", Composite(distance = 2, mass = 1, time = -1)),
        Definition("Angle", Composite(angle = 1)),
        Definition("AngularAcceleration", Composite(angle = 1, time = -2)),
        Definition("AngularMomentum", Composite(mass = 2, distance = 1, time = -1)),
        Definition("AngularVelocity", Composite(angle = 1, time = -1)),
        Definition("Area", Composite(distance = 2)),
        Definition("AreaDensity", Composite(mass = 1, distance = -2)),
        Definition("Capacitance", Composite(time = 4, electricCurrent = 2, mass = -1, distance = -2)),
        Definition("CatalyticEfficiency", Composite(distance = 3, substance = -1, time = -1)),
        Definition("Currency", Composite(currency = 1)),
        Definition("Debt", Composite(currency = 1, time = 1)),
        Definition("Distance", Composite(distance = 1)),
        Definition("ElectricCharge", Composite(electricCurrent = 1, time = 1)),
        Definition("ElectricConductance", Composite(time = 3, electricCurrent = 2, mass = -1, distance = -2)),
        Definition("ElectricConductivity", Composite(time = 3, electricCurrent = 2, mass = -1, distance = -3)),
        Definition("ElectricCurrent", Composite(electricCurrent = 1)),
        Definition("ElectricResistance", Composite(distance = 2, mass = 1, electricCurrent = -2, time = -3)),
        Definition("ElectricTension", Composite(distance = 2, mass = 1, electricCurrent = -1, time = -3)),
        Definition("Energy", Composite(distance = 2, mass = 1, time = -2)),
        Definition("EnergyDensity", Composite(mass = 1, distance = -1, time = -2)),
        Definition("Force", Composite(distance = 1, mass = 1, time = -2)),
        Definition("Frequency", Composite(time = -1)),
        Definition("FuelEfficiency", Composite(distance = -2)),
        Definition("HeatCapacity", Composite(distance = 2, mass = 1, temperature = -1, time = -2)),
        Definition("Illuminance", Composite(luminousIntensity = 1, angle = 2, distance = -2)),
        Definition("Inductance", Composite(distance = 2, mass = 1, electricCurrent = -2, time = -2)),
        Definition("Intensity", Composite(mass = 1, time = -3)),
        Definition("Jerk", Composite(distance = 1, time = -3)),
        Definition("LinearDensity", Composite(mass = 1, distance = -1)),
        Definition("Luminance", Composite(luminousIntensity = 1, distance = -2)),
        Definition("LuminousEnergy", Composite(angle = 2, luminousIntensity = 1, time = 1)),
        Definition("LuminousFlux", Composite(angle = 2, luminousIntensity = 1)),
        Definition("LuminousIntensity", Composite(luminousIntensity = 1)),
        Definition("MagneticFlux", Composite(distance = 2, mass = 1, electricCurrent = -1, time = -2)),
        Definition("MagneticFluxDensity", Composite(mass = 1, electricCurrent = -1, time = -2)),
        Definition("Mass", Composite(mass = 1)),
        Definition("MassDensity", Composite(mass = 1, distance = -3)),
        Definition("MassFlowRate", Composite(mass = 1, time = -1)),
        Definition("Molality", Composite(substance = 1, mass = -1)),
        Definition("Molarity", Composite(substance = 1, distance = -3)),
        Definition("MolarMass", Composite(mass = 1, substance = -1)),
        Definition("MolarConductivity", Composite(time = 3, electricCurrent = 2, substance = -1, mass = -1)),
        Definition("MolarEnergy", Composite(distance = 2, mass = 1, substance = -1, time = -2)),
        Definition("MolarHeatCapacity", Composite(distance = 2, mass = 1, substance = -1, temperature = -1, time = -2)),
        Definition("MolarVolume", Composite(distance = 3, substance = -1)),
        Definition("Momentum", Composite(mass = 1, distance = 1, time = -1)),
        Definition("MomentOfInertia", Composite(distance = 2, mass = 1)),
        Definition("Pace", Composite(time = 1, distance = -1)),
        Definition("Permeability", Composite(distance = 1, mass = 1, electricCurrent = -2, time = -2)),
        Definition("Permittivity", Composite(time = 4, electricCurrent = 2, mass = -1, distance = -3)),
        Definition("Power", Composite(distance = 2, mass = 1, time = -3)),
        Definition("PowerDensity", Composite(mass = 1, distance = -1, time = -3)),
        Definition("Rent", Composite(currency = 1, time = -1)),
        Definition("RadiantExposure", Composite(mass = 1, time = -2)),
        Definition("RadiantIntensity", Composite(distance = 2, mass = 1, angle = -2, time = -3)),
        Definition("ReactionRate", Composite(substance = 1, time = -1, distance = -3)),
        Definition("Scalar", Composite()),
        Definition("Snap", Composite(distance = 1, time = -4)),
        Definition("SolidAngle", Composite(angle = 2)),
        Definition("SpacialFrequency", Composite(distance = -1)),
        Definition("SpecificEnergy", Composite(distance = 2, time = -2)),
        Definition("SpecificHeatCapacity", Composite(distance = 2, temperature = -1, time = -2)),
        Definition("SpecificVolume", Composite(distance = 3, mass = -1)),
        Definition("SpectralIntensity", Composite(distance = 1, mass = 1, angle = -2, time = -3)),
        Definition("Substance", Composite(substance = 1)),
        Definition("Speed", Composite(distance = 1, time = -1)),
        Definition("Temperature", Composite(temperature = 1)),
        Definition("TemperatureGradient", Composite(temperature = 1, distance = -1)),
        Definition("ThermalConductivity", Composite(distance = 1, mass = 1, temperature = -1, time = -3)),
        Definition("ThermalExpansionCoefficient", Composite(temperature = -1)),
        Definition("ThermalResistance", Composite(time = 3, temperature = 1, mass = -1, distance = 2)),
        Definition("Time", Composite(time = 1)),
        Definition("Volume", Composite(distance = 3)),
        Definition("VolumetricFlow", Composite(distance = 3, time = -1)),
        Definition("Yank", Composite(distance = 1, mass = 1, time = -3)))

    fun Composite.max() =
        max(substance, angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

    fun Composite.min() =
        min(substance, angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

    fun min(value: Int, vararg values: Int): Int
    {
        var result = value

        for (v in values)
        {
            result = kotlin.math.min(result, v)
        }

        return result
    }

    fun max(value: Int, vararg values: Int): Int
    {
        var result = value

        for (v in values)
        {
            result = kotlin.math.max(result, v)
        }

        return result
    }
}
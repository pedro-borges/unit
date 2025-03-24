package org.pcb.unit

import kotlin.test.Test
import org.pcb.unit.Earth.airAtomicMass
import org.pcb.unit.Earth.gasConstant
import org.pcb.unit.SIScale.CENTI
import org.pcb.unit.SIScale.MILLI
import org.pcb.unit.amounts.thermodynamics.Bar
import org.pcb.unit.base.Gram
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Kelvin
import org.pcb.unit.base.Meter
import org.pcb.unit.chemisty.ChemicalElement.H
import org.pcb.unit.chemisty.ChemicalElement.He
import org.pcb.unit.config.Configuration
import org.pcb.unit.config.FancyRenderer
import org.pcb.unit.solid.Sphere

class PingPongTest
{
    @Test
    fun testPingPong()
    {
        Configuration.renderer = FancyRenderer()

        val standardRadius = Meter(2, CENTI)
        val standardThickness = Meter(0.55, MILLI)
        val standardBall = Sphere(standardRadius)
        val standardPlasticVolume = standardBall.volume - Sphere(standardThickness).volume
        val standardWeight = Gram(2.7)

        val radius = Meter(12.928, CENTI)
        val plasticVolume = Sphere(radius).volume - Sphere(radius - standardThickness).volume
        val weight = plasticVolume / standardPlasticVolume * standardWeight
        val gasVolume = Sphere(radius - standardThickness).volume
        val pressure = Bar(1013.25, MILLI) // Sea level
        val temperature = Kelvin(293.15) // 20ºC

        val airConstant = gasConstant / airAtomicMass
        val airDensity = pressure / (airConstant * temperature)
        val airWeight = (airDensity * gasVolume).convertTo(GRAM)

        val heliumConstant = gasConstant / He.atomicMass
        val heliumDensity = pressure / (heliumConstant * temperature)
        val heliumWeight = (heliumDensity * gasVolume).convertTo(GRAM)

        val hydrogenConstant = gasConstant / H.atomicMass
        val hydrogenDensity = pressure / (hydrogenConstant * temperature)
        val hydrogenWeight = (hydrogenDensity * gasVolume).convertTo(GRAM)

        println(weight - (airWeight - airWeight))
        println(weight - (airWeight - heliumWeight))
        println(weight - (airWeight - hydrogenWeight))
    }
}
package org.pcb.unit.units.test

import org.pcb.unit.Magnitude
import org.pcb.unit.Magnitude.DECA
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Angle
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.LuminousIntensity
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Temperature
import org.pcb.unit.dimension.Time

open class ANGLE(magnitude: Magnitude) : Unit<Angle>(Angle::class, magnitude.symbol + "M", UNI, magnitude.scale)
object ANGLE1 : ANGLE(UNI)
object ANGLE2 : ANGLE(DECA)

open class CURRENCY(magnitude: Magnitude) : Unit<Currency>(Currency::class, magnitude.symbol + "C", UNI, magnitude.scale)
object CURRENCY1 : CURRENCY(UNI)
object CURRENCY2 : CURRENCY(DECA)

open class DISTANCE(magnitude: Magnitude) : Unit<Distance>(Distance::class, magnitude.symbol + "D", UNI, magnitude.scale)
object DISTANCE1 : DISTANCE(UNI)
object DISTANCE2 : DISTANCE(DECA)

open class ELECTRICCURRENT(magnitude: Magnitude) : Unit<ElectricCurrent>(ElectricCurrent::class, magnitude.symbol + "EC", UNI, magnitude.scale)
object ELECTRICCURRENT1 : ELECTRICCURRENT(UNI)
object ELECTRICCURRENT2 : ELECTRICCURRENT(DECA)

open class LUMINOUSINTENSITY(magnitude: Magnitude) : Unit<LuminousIntensity>(LuminousIntensity::class, magnitude.symbol + "EC", UNI, magnitude.scale)
object LUMINOUSINTENSITY1 : LUMINOUSINTENSITY(UNI)
object LUMINOUSINTENSITY2 : LUMINOUSINTENSITY(DECA)

open class MASS(magnitude: Magnitude) : Unit<Mass>(Mass::class, magnitude.symbol + "M", UNI, magnitude.scale)
object MASS1 : MASS(UNI)
object MASS2 : MASS(DECA)

open class TIME(magnitude: Magnitude) : Unit<Time>(Time::class, magnitude.symbol + "T", UNI, magnitude.scale)
object TIME1 : TIME(UNI)
object TIME2 : TIME(DECA)

open class TEMPERATURE(magnitude: Magnitude) : Unit<Temperature>(Temperature::class, magnitude.symbol + "P", UNI, magnitude.scale)
object TEMPERATURE1 : TEMPERATURE(UNI)
object TEMPERATURE2 : TEMPERATURE(DECA)

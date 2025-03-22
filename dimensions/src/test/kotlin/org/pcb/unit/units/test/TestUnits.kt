package org.pcb.unit.units.test

import java.math.BigDecimal.TEN
import org.pcb.unit.Magnitude
import org.pcb.unit.SIScale.DECA
import org.pcb.unit.SIScale.DECI
import org.pcb.unit.SIScale.UNI
import org.pcb.unit.Unit
import org.pcb.unit.dimensions.Angle
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Frequency
import org.pcb.unit.dimensions.LuminousIntensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.power

open class ANGLE(magnitude: Magnitude) : Unit<Angle>(Angle::class, magnitude.symbol + "M", TEN.power(magnitude.power), UNI)
object ANGLE1 : ANGLE(UNI)
object ANGLE2 : ANGLE(DECA)

open class CURRENCY(magnitude: Magnitude) : Unit<Currency>(Currency::class, magnitude.symbol + "C", TEN.power(magnitude.power), UNI)
object CURRENCY1 : CURRENCY(UNI)
object CURRENCY2 : CURRENCY(DECA)

open class DISTANCE(magnitude: Magnitude) : Unit<Distance>(Distance::class, magnitude.symbol + "L", TEN.power(magnitude.power), UNI)
object DISTANCE1 : DISTANCE(UNI)
object DISTANCE2 : DISTANCE(DECA)

open class ELECTRICCURRENT(magnitude: Magnitude) : Unit<ElectricCurrent>(ElectricCurrent::class, magnitude.symbol + "I", TEN.power(magnitude.power), UNI)
object ELECTRICCURRENT1 : ELECTRICCURRENT(UNI)
object ELECTRICCURRENT2 : ELECTRICCURRENT(DECA)

open class FREQUENCY(magnitude: Magnitude) : Unit<Frequency>(Frequency::class, magnitude.symbol + "F", TEN.power(magnitude.power), UNI)
object FREQUENCY1 : FREQUENCY(UNI)
object FREQUENCY2 : FREQUENCY(DECI)

open class LUMINOUSINTENSITY(magnitude: Magnitude) : Unit<LuminousIntensity>(LuminousIntensity::class, magnitude.symbol + "EC", TEN.power(magnitude.power), UNI)
object LUMINOUSINTENSITY1 : LUMINOUSINTENSITY(UNI)
object LUMINOUSINTENSITY2 : LUMINOUSINTENSITY(DECA)

open class MASS(magnitude: Magnitude) : Unit<Mass>(Mass::class, magnitude.symbol + "M", TEN.power(magnitude.power), UNI)
object MASS1 : MASS(UNI)
object MASS2 : MASS(DECA)

open class NUMBER(magnitude: Magnitude) : Unit<Scalar>(Scalar::class, magnitude.symbol, TEN.power(magnitude.power), UNI)
object NUMBER1 : NUMBER(UNI)
object NUMBER2 : NUMBER(DECI)

open class TIME(magnitude: Magnitude) : Unit<Time>(Time::class, magnitude.symbol + "T", TEN.power(magnitude.power), UNI)
object TIME1 : TIME(UNI)
object TIME2 : TIME(DECA)

open class TEMPERATURE(magnitude: Magnitude) : Unit<Temperature>(Temperature::class, magnitude.symbol + "K", TEN.power(magnitude.power), UNI)
object TEMPERATURE1 : TEMPERATURE(UNI)
object TEMPERATURE2 : TEMPERATURE(DECA)

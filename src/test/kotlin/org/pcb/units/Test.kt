package org.pcb.units

import kotlin.test.Test
import org.pcb.units.amounts.Minutes
import org.pcb.units.amounts.Seconds
import org.pcb.units.amounts.mechanics.Kilograms
import org.pcb.units.amounts.mechanics.Newtons
import org.pcb.units.amounts.mechanics.Newtons.Companion.NEWTONS
import org.pcb.units.amounts.metric.Meters
import org.pcb.units.domain.Speed
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.metric.Meter.Companion.METER
import org.pcb.units.unit.metric.Second.Companion.SECOND

class Test
{
    @Test
    fun test()
    {
//        println(Meters(10))
//
//        println(Meters(10) / Seconds(2) * Seconds(4))
//
//        println(Meters(1) + Inches(2))

//        assertEquals(Feet(3600.0) / Seconds(60.0),
//                     Feet(3600.0) / Seconds(60.0) / Minutes(1.0) * Seconds(60))
//
//        assertEquals(Feet(3600.0) / Seconds(60.0),
//                     Feet(3600.0) / Seconds(60.0) / Minutes(1.0) * Minutes(1))

        println(Meters(120) / Minutes(2))
        println((Meters(120) / Minutes(2))
                    .using(CompositeUnit(units = mapOf(METER to 1, SECOND to -1),
                                         domain = Speed::class)))

        println(Meters(3600) / Minutes(1) / Seconds(60))
        println(Meters(3600) / Minutes(1) / Seconds(60) * Seconds(60))
        println(Meters(3600) / Minutes(1) / Seconds(60) * Minutes(1))


        println(Meters(3600.0) / Seconds(60.0) / Minutes(1.0) * Kilograms(2))
        println((Meters(3600.0) / Seconds(60.0) / Minutes(1.0) * Kilograms(2)).using(NEWTONS))
        println(Newtons(2))
    }
}
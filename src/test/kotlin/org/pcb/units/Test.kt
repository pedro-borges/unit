package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amounts.Minutes
import org.pcb.units.amounts.Seconds
import org.pcb.units.amounts.imperial.Feet
import org.pcb.units.amounts.mechanics.Kilograms
import org.pcb.units.amounts.mechanics.Newtons
import org.pcb.units.amounts.metric.Meters
import org.pcb.units.unit.mechanics.Newton.Companion.NEWTONS

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

        assertEquals(Feet(3600.0) / Seconds(60.0),
                     Feet(3600.0) / Seconds(60.0) / Minutes(1.0) * Seconds(60))

        assertEquals(Feet(3600.0) / Seconds(60.0),
                     Feet(3600.0) / Seconds(60.0) / Minutes(1.0) * Minutes(1))

        println(Meters(3600.0) / Seconds(60.0) / Minutes(1.0) * Kilograms(2))
        println((Meters(3600.0) / Seconds(60.0) / Minutes(1.0) * Kilograms(2)).using(NEWTONS))
        println(Newtons(2))
    }
}
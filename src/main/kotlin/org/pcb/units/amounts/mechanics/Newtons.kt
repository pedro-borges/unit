package org.pcb.units.amounts.mechanics

import org.pcb.units.amounts.generic.ForceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.mechanics.Kilogram
import org.pcb.units.unit.mechanics.Kilogram.Companion.KILOGRAM
import org.pcb.units.unit.metric.Meter
import org.pcb.units.unit.metric.Meter.Companion.METER
import org.pcb.units.unit.time.Second
import org.pcb.units.unit.time.Second.Companion.SECOND

class Newtons(amount: Number)
    : ForceAmount<Kilogram, Meter, Second, Second>(amount.toBigDecimal(), KILOGRAM, METER, SECOND, SECOND)
{
    companion object
    {
        val NEWTONS = Newtons(0).unit
    }
}
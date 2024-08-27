package org.pcb.units.amounts.mechanics

import org.pcb.units.amounts.domain.ForceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.mechanics.Kilogram.Companion.KILOGRAMS
import org.pcb.units.unit.mechanics.Newton.Companion.NEWTONS
import org.pcb.units.unit.metric.Meter.Companion.METERS
import org.pcb.units.unit.metric.Second.Companion.SECONDS

class Newtons(amount: Number)
    : ForceAmount(amount.toBigDecimal(), METERS, KILOGRAMS, SECONDS, SECONDS)
{
}
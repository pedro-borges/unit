package org.pcb.units.amounts.metric

import org.pcb.units.amounts.generic.SpaceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.metric.Meter
import org.pcb.units.unit.metric.Meter.Companion.METER

class Meters(amount: Number)
    : SpaceAmount<Meter>(amount.toBigDecimal(), METER)
{
}
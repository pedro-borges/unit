package org.pcb.units.amounts.metric

import org.pcb.units.amounts.domain.SpaceAmount
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.metric.Meter.Companion.METERS

class Meters(amount: Number)
    : SpaceAmount(amount.toBigDecimal(), METERS)
{
}
package org.pcb.units.amount.metric

import org.pcb.units.amount.SpaceAmount
import org.pcb.units.toBigBigDecimal
import org.pcb.units.unit.metric.Meter

@Suppress("unused")
class Meters(amount: Number)
    : SpaceAmount<Meter>(amount.toBigBigDecimal(), Meter)

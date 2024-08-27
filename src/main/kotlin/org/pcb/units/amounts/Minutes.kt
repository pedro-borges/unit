package org.pcb.units.amounts

import org.pcb.units.amounts.domain.TimeAmount
import org.pcb.units.unit.metric.Minute.Companion.MINUTES
import org.pcb.units.unit.metric.Second.Companion.SECONDS

class Minutes(amount: Number)
    : TimeAmount(amount, MINUTES)
{
}
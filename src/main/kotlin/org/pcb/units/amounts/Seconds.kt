package org.pcb.units.amounts

import org.pcb.units.amounts.domain.TimeAmount
import org.pcb.units.unit.metric.Second.Companion.SECONDS

class Seconds(amount: Number)
    : TimeAmount(amount, SECONDS)
{
}
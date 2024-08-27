package org.pcb.units.amounts

import org.pcb.units.amounts.generic.TimeAmount
import org.pcb.units.unit.metric.Minute.Companion.MINUTE

class Minutes(amount: Number)
    : TimeAmount(amount, MINUTE)
{
}
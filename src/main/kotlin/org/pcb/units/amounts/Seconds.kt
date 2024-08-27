package org.pcb.units.amounts

import org.pcb.units.amounts.generic.TimeAmount
import org.pcb.units.unit.metric.Second.Companion.SECOND

class Seconds(amount: Number)
    : TimeAmount(amount, SECOND)
{
}
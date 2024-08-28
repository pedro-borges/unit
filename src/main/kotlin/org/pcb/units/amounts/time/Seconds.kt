package org.pcb.units.amounts.time

import org.pcb.units.amounts.generic.TimeAmount
import org.pcb.units.unit.time.Second
import org.pcb.units.unit.time.Second.Companion.SECOND

class Seconds(amount: Number)
    : TimeAmount<Second>(amount, SECOND)
{
}
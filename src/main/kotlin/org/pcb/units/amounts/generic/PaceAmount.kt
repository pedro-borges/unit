package org.pcb.units.amounts.generic

import org.pcb.units.domain.Pace
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigDecimal
import org.pcb.units.unit.CompositeUnit
import org.pcb.units.unit.Unit

open class PaceAmount(amount: Number,
                      val time: Unit<Time>,
                      val space: Unit<Space>)
    : Amount<Pace>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       units = mapOf(time to 1,
                                     space to -1),
                       domain = Pace::class))
{
    operator fun times(that: SpaceAmount) =
        when
        {
            space.cannon == that.unit.cannon ->
                TimeAmount(amount = amount * that.amount,
                           time = time)
            else                             ->
                TimeAmount(amount = amount / space.cannon * that.amount * that.unit.cannon,
                           time = time)
        }
}
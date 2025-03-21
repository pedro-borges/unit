package org.pcb.unit.shape

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.min
import org.pcb.unit.power
import org.pcb.unit.solid.Cuboid

open class Rectangle<L : Unit<Distance>>(val length: DistanceAmount<L>,
                                         val width: DistanceAmount<L>)
    : Shape<L>
{
    override val narrowest = min(length, width)
    override val widest = DistanceAmount(amount = (length.amount.power(2) + width.amount.power(2)).sqrt(calculationMathContext),
                                         unit = length.distance)
    override val perimeter = length * 2 + width * 2
    override val area = length * width
    override val edges = 4
    override val vertices = 4

    @JvmName("timesHeight")
    operator fun <L : Unit<Distance>> times(height: DistanceAmount<L>) =
        Cuboid(base = this,
               height = DistanceAmount(amount = height.amount * convert(height.distance to length.distance),
                                       unit = length.distance))

    operator fun times(height: DistanceAmount<L>) =
        Cuboid(base = this,
               height = height)
}

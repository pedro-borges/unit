package org.pcb.unit.shape

import org.pcb.unit.Constants.PI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.power
import org.pcb.unit.solid.Cylinder
import org.pcb.unit.times


class Circle<L : Unit<Distance>>(val radius: DistanceAmount<L>)
    : Shape<L>
{
    val diameter = radius * 2

    override val narrowest = DistanceAmount(amount = diameter.amount,
                                            unit = radius.distance)
    override val widest = DistanceAmount(amount = diameter.amount,
                                         unit = radius.distance)
    override val perimeter = DistanceAmount(amount = PI * radius.amount * 2,
                                            unit = radius.distance)
    override val area = AreaAmount(amount = PI * radius.amount.power(2),
                                   unit = radius.distance)
    override val edges = 1
    override val vertices = 0

    @JvmName("timesHeight")
    operator fun <L : Unit<Distance>> times(height: DistanceAmount<L>) =
        Cylinder(base = this,
                 height = DistanceAmount(amount = height.amount * convert(height.distance to radius.distance),
                                         unit = radius.distance))

    operator fun times(height: DistanceAmount<L>) =
        Cylinder(base = this,
                 height = height)
}

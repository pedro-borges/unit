package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.AccelerationAmount
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.FrequencyAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.mass.Gram.GRAM
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class AcceleartionAmountTest
{
    @Test
    fun plusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(1, METER, SECOND),
            actual = AccelerationAmount(0, METER, SECOND) + AccelerationAmount(1, METER, SECOND))
    }

    @Test
    fun plusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(1, FOOT, SECOND),
            actual = AccelerationAmount(0, FOOT, SECOND) + AccelerationAmount(43200, INCH, MINUTE))
    }

    @Test
    fun minusSameAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(0, METER, SECOND),
            actual = AccelerationAmount(1, METER, SECOND) - AccelerationAmount(1, METER, SECOND))
    }

    @Test
    fun minusOtherAcceleration()
    {
        assertEquals(
            expected = AccelerationAmount(0, FOOT, SECOND),
            actual = AccelerationAmount(1, FOOT, SECOND) - AccelerationAmount(43200, INCH, MINUTE))
    }

    @Test
    fun divSameAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = AccelerationAmount(2, METER, SECOND) / AccelerationAmount(2, METER, SECOND))
    }

    @Test
    fun divOtherAcceleration()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = AccelerationAmount(2, FOOT, SECOND) / AccelerationAmount(86400, INCH, MINUTE))
    }

    @Test
    fun timesSameTime()
    {
        assertEquals(
            expected = SpeedAmount(4, METER, SECOND),
            actual = AccelerationAmount(2, METER, SECOND) * TimeAmount(2, SECOND))
    }

    @Test
    fun timesOtherTime()
    {
        assertEquals(
            expected = SpeedAmount(240, METER, SECOND),
            actual = AccelerationAmount(2, METER, SECOND) * TimeAmount(2, MINUTE))
    }

    @Test
    fun timesSameSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(4, SECOND),
            actual = AccelerationAmount(2, METER, SECOND) * SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun timesOtherSpeed()
    {
        assertEquals(
            expected = FrequencyAmount(4, SECOND),
            actual = AccelerationAmount(2, FOOT, SECOND) * SpeedAmount(86400, INCH, MINUTE))
    }

    @Test
    fun timesMass()
    {
        assertEquals(
            expected = ForceAmount(4, GRAM, METER, SECOND),
            actual = AccelerationAmount(2, METER, SECOND) * MassAmount(2, GRAM))
    }


}
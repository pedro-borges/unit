package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.PaceAmount
import org.pcb.units.amount.ScalarAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.unit.metric.Meter
import org.pcb.units.unit.time.Minute
import org.pcb.units.unit.time.Second

class TimeAmountTest
{
    @Test
    fun plusSameTime()
    {
        assertEquals(
            expected = TimeAmount(3, Second),
            actual = TimeAmount(1, Second) + TimeAmount(2, Second))
    }

    @Test
    fun plusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(62, Second),
            actual = TimeAmount(2, Second) + TimeAmount(1, Minute))
    }

    @Test
    fun minusSameTime()
    {
        assertEquals(
            expected = TimeAmount(1, Second),
            actual = TimeAmount(3, Second) - TimeAmount(2, Second))
    }

    @Test
    fun minusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(2, Second),
            actual = TimeAmount(62, Second) - TimeAmount(1, Minute))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = TimeAmount(8, Second) / TimeAmount(4, Second))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = TimeAmount(120, Second) / TimeAmount(1, Minute))
    }

    @Test
    fun divSpace()
    {
        assertEquals(
            expected = PaceAmount(2, Second, Meter),
            actual = TimeAmount(8, Second) / SpaceAmount(4, Meter))
    }
}
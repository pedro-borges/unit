package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.PaceAmount
import org.pcb.units.amount.ScalarAmount
import org.pcb.units.amount.SpaceAmount
import org.pcb.units.amount.TimeAmount
import org.pcb.units.amount.metric.Meters.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class TimeAmountTest
{
    @Test
    fun plusSameTime()
    {
        assertEquals(
            expected = TimeAmount(3, SECOND),
            actual = TimeAmount(1, SECOND) + TimeAmount(2, SECOND))
    }

    @Test
    fun plusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(62, SECOND),
            actual = TimeAmount(2, SECOND) + TimeAmount(1, MINUTE))
    }

    @Test
    fun minusSameTime()
    {
        assertEquals(
            expected = TimeAmount(1, SECOND),
            actual = TimeAmount(3, SECOND) - TimeAmount(2, SECOND))
    }

    @Test
    fun minusOtherTime()
    {
        assertEquals(
            expected = TimeAmount(2, SECOND),
            actual = TimeAmount(62, SECOND) - TimeAmount(1, MINUTE))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = TimeAmount(8, SECOND) / TimeAmount(4, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ScalarAmount(2),
            actual = TimeAmount(120, SECOND) / TimeAmount(1, MINUTE))
    }

    @Test
    fun divSpace()
    {
        assertEquals(
            expected = PaceAmount(2, SECOND, METER),
            actual = TimeAmount(8, SECOND) / SpaceAmount(4, METER))
    }
}
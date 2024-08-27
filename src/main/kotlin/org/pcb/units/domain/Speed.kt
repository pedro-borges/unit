package org.pcb.units.domain

interface Speed : Domain
{
    operator fun times(time: Time): Space
    operator fun div(time: Time): Acceleration
}
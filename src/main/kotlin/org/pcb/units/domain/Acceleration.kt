package org.pcb.units.domain

interface Acceleration : Domain
{
    operator fun times(time: Time): Speed
    operator fun times(mass: Mass): Force
}
package org.pcb.units.domain

interface Force : Domain
{
    operator fun div(mass: Mass): Acceleration
    operator fun div(acceleration: Acceleration): Mass
}
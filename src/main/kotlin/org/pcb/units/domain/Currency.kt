package org.pcb.units.domain

interface Currency : Domain
{
    operator fun div(time: Time): Rent
    operator fun times(time: Time): Debt
}
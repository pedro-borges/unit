package org.pcb.units.domain

interface Debt : Domain
{
    operator fun div(time: Time): Currency
}
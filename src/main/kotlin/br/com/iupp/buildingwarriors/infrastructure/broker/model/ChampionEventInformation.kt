package br.com.iupp.buildingwarriors.infrastructure.broker.model

data class ChampionEventInformation(
    val operation: ChampionOperations,
    val championRequest: ChampionEvent
)

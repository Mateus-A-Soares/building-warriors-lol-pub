package br.com.iupp.buildingwarriors.infrastructure.broker.service

import br.com.iupp.buildingwarriors.core.ports.NatsServicePort
import br.com.iupp.buildingwarriors.infrastructure.broker.client.ChampionClient
import br.com.iupp.buildingwarriors.infrastructure.broker.model.ChampionEvent
import br.com.iupp.buildingwarriors.infrastructure.broker.model.ChampionEventInformation
import br.com.iupp.buildingwarriors.infrastructure.broker.model.ChampionOperations.*
import javax.inject.Singleton

@Singleton
class NatsService(private val client : ChampionClient) : NatsServicePort {

    override fun createChampionEvent(championEvent: ChampionEvent) {
        client.publishEvent(ChampionEventInformation(CREATE, championEvent.apply { id = null }))
    }

    override fun updateChampionEvent(championEvent: ChampionEvent) {
        client.publishEvent(ChampionEventInformation(UPDATE, championEvent))
    }

    override fun deleteChampionEvent(id: String) {
        client.publishEvent(ChampionEventInformation(DELETE, ChampionEvent(id = id)))
    }
}
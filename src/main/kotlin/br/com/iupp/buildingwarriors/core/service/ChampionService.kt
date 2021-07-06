package br.com.iupp.buildingwarriors.core.service

import br.com.iupp.buildingwarriors.core.mapper.ChampionMapper.Companion.championToChampionEvent
import br.com.iupp.buildingwarriors.core.mapper.ChampionMapper.Companion.championToChampionResponse
import br.com.iupp.buildingwarriors.core.model.Champion
import br.com.iupp.buildingwarriors.core.ports.ChampionServicePort
import br.com.iupp.buildingwarriors.core.ports.NatsServicePort
import br.com.iupp.buildingwarriors.entrypoint.controller.response.ChampionResponse
import javax.inject.Singleton

@Singleton
class ChampionService(private val service: NatsServicePort): ChampionServicePort {

    override fun createRequest(champion: Champion): ChampionResponse = with(champion){
        service.createChampionEvent(championToChampionEvent(this))
        championToChampionResponse(this)
    }

    override fun updateRequest(id: String, champion: Champion): ChampionResponse = with(champion){
        service.updateChampionEvent(championToChampionEvent(id = id, champion = this))
        championToChampionResponse(this)
    }

    override fun deleteRequest(id: String) {
        service.deleteChampionEvent(id)
    }
}
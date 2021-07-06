package br.com.iupp.buildingwarriors.infrastructure.broker.client

import br.com.iupp.buildingwarriors.infrastructure.broker.model.ChampionEventInformation
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject
import javax.inject.Singleton

@NatsClient
@Singleton
interface ChampionClient {

    @Subject("champion")
    fun publishEvent(eventInformation: ChampionEventInformation)
}

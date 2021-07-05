package br.com.iupp.buildingwarriors.core.model

import javax.validation.constraints.Size

data class Champion(
    @field:Size(max = 50)
    val name: String? = null,
    @field:Size(max = 255)
    val shortDescription: String? = null,
    val role: ChampionRole? = null,
    val difficulty: ChampionDifficulty? = null
)
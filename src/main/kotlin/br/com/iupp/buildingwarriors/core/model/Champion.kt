package br.com.iupp.buildingwarriors.core.model

data class Champion(
    val name: String? = null,
    val shortDescription: String? = null,
    val role: ChampionRole? = null,
    val difficulty: ChampionDifficulty? = null
)

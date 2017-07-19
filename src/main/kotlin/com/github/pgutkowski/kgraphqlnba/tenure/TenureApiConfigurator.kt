package com.github.pgutkowski.kgraphqlnba.tenure

import com.github.pgutkowski.kgraphql.schema.dsl.SchemaBuilder
import com.github.pgutkowski.kgraphqlnba.ApiConfigurator
import com.github.pgutkowski.kgraphqlnba.NotFoundException
import com.github.pgutkowski.kgraphqlnba.player.PlayerRepository
import org.springframework.stereotype.Component

@Component
open class TenureApiConfigurator(val tenures: TenureRepository, val players: PlayerRepository) : ApiConfigurator {
    override val config: SchemaBuilder.() -> Unit = {
        query("tenures"){
            //not optimal implementation, but good enough for demo app
            resolver { playerId: Int ->
                val player = players.findOne(playerId) ?: throw NotFoundException("Player with id $playerId does not exist")
                tenures.findByPlayer(player)
            }
        }
    }
}
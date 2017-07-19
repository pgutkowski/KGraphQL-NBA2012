package com.github.pgutkowski.kgraphqlnba.player

import com.github.pgutkowski.kgraphql.schema.dsl.SchemaBuilder
import com.github.pgutkowski.kgraphqlnba.ApiConfigurator
import com.github.pgutkowski.kgraphqlnba.team.Team
import com.github.pgutkowski.kgraphqlnba.tenure.Tenure
import com.github.pgutkowski.kgraphqlnba.tenure.TenureRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
open class PlayerApiConfigurator(val players: PlayerRepository, val tenures: TenureRepository) : ApiConfigurator {
    override val config: SchemaBuilder.() -> Unit = {

        query("players"){
            resolver { page: Int, size: Int -> players.findAll(PageRequest(page, size)).content }.withArgs {
                arg<Int> { name = "page"; defaultValue = 0 }
                arg<Int> { name = "size"; defaultValue = 10 }
            }
        }

        query("player"){
            resolver { id: Int -> players.findOne(id) }
        }

        type<Player>{
            property<Team?>("team"){
                resolver { player ->
                    tenures.findTopByPlayerOrderByStartDate(player).team
                }
            }

            property<List<Tenure>>("tenures"){
                resolver { player ->
                    tenures.findByPlayer(player)
                }
            }
        }

    }
}
package com.github.pgutkowski.kgraphqlnba.game

import com.github.pgutkowski.kgraphql.schema.dsl.SchemaBuilder
import com.github.pgutkowski.kgraphqlnba.ApiConfigurator
import com.github.pgutkowski.kgraphqlnba.NotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
open class GameApiConfigurator(val games: GameRepository) : ApiConfigurator {
    override val config: SchemaBuilder.() -> Unit = {
        query("game"){
            resolver { id: String -> games.findOne(id) ?: throw NotFoundException("Game with id $id does not exist") }
        }

        query("games"){
            resolver { page: Int, size: Int -> games.findAll(PageRequest(page, size)).content }.withArgs {
                arg<Int> { name = "page"; defaultValue = 0 }
                arg<Int> { name = "size"; defaultValue = 10 }
            }
        }
    }

}
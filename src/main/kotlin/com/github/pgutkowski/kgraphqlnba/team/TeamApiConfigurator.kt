package com.github.pgutkowski.kgraphqlnba.team

import com.github.pgutkowski.kgraphql.schema.dsl.SchemaBuilder
import com.github.pgutkowski.kgraphqlnba.ApiConfigurator
import com.github.pgutkowski.kgraphqlnba.NotFoundException
import org.springframework.stereotype.Component

@Component
open class TeamApiConfigurator(val teams: TeamRepository) : ApiConfigurator{
    override val config: SchemaBuilder.() -> Unit = {
        query("team"){
            resolver { id: String -> teams.findOne(id) ?: throw NotFoundException("Team with id $id does not exist") }
        }
    }
}
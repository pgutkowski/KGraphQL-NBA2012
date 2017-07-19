package com.github.pgutkowski.kgraphqlnba

import com.github.pgutkowski.kgraphql.KGraphQL
import com.github.pgutkowski.kgraphql.schema.Schema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
open class ApiConfiguration(val configs: List<ApiConfigurator>) {

    @Bean
    fun getSchema() : Schema = KGraphQL.schema {

        stringScalar<LocalDate> {
            serialize = { date -> date.toString() }
            deserialize = { dateString -> LocalDate.parse(dateString)}
        }

        configs.forEach { it.config(this) }
    }

}
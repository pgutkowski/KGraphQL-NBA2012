package com.github.pgutkowski.kgraphqlnba

import com.github.pgutkowski.kgraphql.schema.dsl.SchemaBuilder


interface ApiConfigurator {
    val config : SchemaBuilder.() -> Unit
}
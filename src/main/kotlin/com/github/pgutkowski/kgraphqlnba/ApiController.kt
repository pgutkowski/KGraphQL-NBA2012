package com.github.pgutkowski.kgraphqlnba

import com.github.pgutkowski.kgraphql.schema.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
open class ApiController(val schema : Schema) {

    @GetMapping("/api", produces = arrayOf("application/json"))
    @ResponseBody
    fun executeQuery(@RequestParam("query") query : String): String {
        return schema.execute(query)
    }
}


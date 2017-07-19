package com.github.pgutkowski.kgraphqlnba

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.pgutkowski.kgraphqlnba.game.Game
import com.github.pgutkowski.kgraphqlnba.player.Player
import com.github.pgutkowski.kgraphqlnba.team.Team
import com.github.pgutkowski.kgraphqlnba.tenure.Tenure
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.Matchers.startsWith
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Autowired
	lateinit var objectMapper : ObjectMapper

	@Test
	fun getPlayer() {
		val player = query<Player>("{player(id: 1){id, name}}", "player")
		assertThat(player.id, equalTo(1))
		assertThat(player.name, equalTo("Howard, Josh"))
	}

	@Test
	fun getPlayersTeam() {
		val playersTeam = query<Team>("{player(id: 1){id, name, team{id, minutes}}}", "player", "team")
		assertThat(playersTeam.id, equalTo("UTA"))
		assertThat(playersTeam.minutes, equalTo(2925.0))
	}

	@Test
	fun getTeam() {
		val team = query<Team>("{team(id: \"UTA\"){id, minutes}}", "team")
		assertThat(team.id, equalTo("UTA"))
		assertThat(team.minutes, equalTo(2925.0))
	}

	@Test
	fun getPlayerTenures() {
		val tenures = queryList<Tenure>("{player(id: 16){id, tenures{team{id}, position, comment}}}", "player", "tenures")
		assertThat(tenures[0].team?.id, equalTo("TOR"))
		assertThat(tenures[0].position, equalTo(1.99))
		assertThat(tenures[0].comment, equalTo("SeasonStart"))

		assertThat(tenures[1].team?.id, equalTo("IND"))
		assertThat(tenures[1].position, equalTo(1.99))
		assertThat(tenures[1].comment, equalTo("20120320LACIND"))
	}

	@Test
	fun getTenuresByPlayerId(){
		val tenures = queryList<Tenure>("{tenures(playerId: 16){team{id}, position, comment}}", "tenures")
		assertThat(tenures[0].team?.id, equalTo("TOR"))
		assertThat(tenures[0].position, equalTo(1.99))
		assertThat(tenures[0].comment, equalTo("SeasonStart"))

		assertThat(tenures[1].team?.id, equalTo("IND"))
		assertThat(tenures[1].position, equalTo(1.99))
		assertThat(tenures[1].comment, equalTo("20120320LACIND"))
	}

	@Test
	fun getGames(){
		val games = queryList<Game>("{games{id, homeTeam{id}, awayTeam{id}}}", "games")
		assertThat(games, hasSize(10))
		games.forEach { game ->
			assertThat(game.id, startsWith("2011"))
			assertThat(game.homeTeam?.id, notNullValue())
			assertThat(game.awayTeam?.id, notNullValue())
		}
	}

	@Test
	fun getGame(){
		val game = query<Game>("{game(id: \"20111225BOSNYK\"){id, homeTeam{id}, awayTeam{id}}}", "game")
		assertThat(game.id, equalTo("20111225BOSNYK"))
		assertThat(game.homeTeam?.id, equalTo("NYK"))
		assertThat(game.awayTeam?.id, equalTo("BOS"))
	}

	inline fun <reified T>queryList(fullQuery: String, vararg fieldPath: String): List<T> {
		val responseBody = restTemplate.getForObject("/api?query={query}", JsonNode::class.java, fullQuery)
		return objectMapper
				.readerFor(objectMapper.typeFactory.constructCollectionLikeType(List::class.java, T::class.java))
				.readValue<List<T>>(responseBody["data"].extract(fieldPath.toList()))
	}

	inline fun <reified T>query(fullQuery: String, vararg fieldPath: String): T {
		val responseBody = restTemplate.getForObject("/api?query={query}", JsonNode::class.java, fullQuery)
		return objectMapper
				.readerFor(T::class.java)
				.readValue<T>(responseBody["data"].extract(fieldPath.toList()))
	}

	fun JsonNode.extract(path: List<String>) : JsonNode {
		return if(path.isNotEmpty()) get(path[0]).extract(path.drop(1)) else this
    }
}

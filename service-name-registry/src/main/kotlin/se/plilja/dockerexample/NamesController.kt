package se.plilja.dockerexample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.web.bind.annotation.*


@RestController("/")
class NamesController(@Autowired var jdbcTemplate: NamedParameterJdbcTemplate) {

    @GetMapping("/names")
    fun get(): ResponseEntity<List<String>> {
        val names = jdbcTemplate.query("SELECT NAME FROM NAMES", RowMapper { resultSet, i -> resultSet.getString("NAME") })
        return ResponseEntity.ok(names)
    }

    @PostMapping("/names/{name}")
    fun addName(@PathVariable name: String): ResponseEntity<Void> {
        val params = MapSqlParameterSource().addValue("name", name);
        jdbcTemplate.update("INSERT INTO NAMES(NAME) VALUES (:name)", params);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/names/{name}")
    fun removeName(@PathVariable name: String): ResponseEntity<Void> {
        val params = MapSqlParameterSource().addValue("name", name);
        jdbcTemplate.update("DELETE FROM NAMES WHERE NAME = :name", params);
        return ResponseEntity.ok().build();
    }

}
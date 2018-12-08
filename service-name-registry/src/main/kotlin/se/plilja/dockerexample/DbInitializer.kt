package se.plilja.dockerexample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class DbInitializer(@Autowired var jdbcTemplate: JdbcTemplate) {

    @PostConstruct
    fun postConstruct() {
        if (jdbcTemplate.queryForObject("SELECT COUNT(*) FROM NAMES", Int::class.java) == 0) {
            jdbcTemplate.execute("INSERT INTO NAMES (NAME) VALUES ('Sten')")
            jdbcTemplate.execute("INSERT INTO NAMES (NAME) VALUES ('Kalle')")
            jdbcTemplate.execute("INSERT INTO NAMES (NAME) VALUES ('Bertil')")
            jdbcTemplate.execute("INSERT INTO NAMES (NAME) VALUES ('Börje')")
        }
    }

}
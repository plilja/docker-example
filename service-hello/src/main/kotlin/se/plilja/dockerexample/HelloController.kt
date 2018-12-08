package se.plilja.dockerexample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate


@RestController("/")
class HelloController() {

    @GetMapping("/hello/{name}")
    fun get(@PathVariable("name") name: String): ResponseEntity<String> {
        return ResponseEntity.ok("Hello ${name}!")
    }

    @GetMapping("/hellos")
    fun sayHelloToEveryOne(): ResponseEntity<List<String>> {
        val restTemplate = RestTemplate()
        val names = restTemplate.getForObject("http://service-name-registry/names", Array<String>::class.java)
        return ResponseEntity.ok(names!!.map { "Hello ${it}" }.toList())
    }

}
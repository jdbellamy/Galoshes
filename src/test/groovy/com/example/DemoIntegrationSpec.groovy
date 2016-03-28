package com.example

import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringApplicationConfiguration(classes = DemoApplication)
@WebAppConfiguration
class DemoIntegrationSpec extends Specification {

    @Autowired
    WebApplicationContext context

    MockMvc mvc

    def setup() {
        MockitoAnnotations.initMocks(this)
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    void "greeting api returns valid result"() {
        when:
        def response = this.mvc.perform(get("/api/hello")
                .accept(MediaType.parseMediaType('application/json;charset=UTF-8')))

        then:
        response.andExpect(status().isOk())
        response.andExpect(content().contentType('application/json;charset=UTF-8'))
        response.andExpect(content().json('{phrase: Hi, who: Me}'))
    }
}
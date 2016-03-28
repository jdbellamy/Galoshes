package com.example

import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringApplicationConfiguration(classes = DemoApplication)
@WebAppConfiguration
class ViewHelperSpec extends Specification {

    @Autowired
    ViewHelper helper

    @Shared
    def fixture = [
            'AAAAA': 'AAAAA',
            'aaAAA': 'AaAAA',
            'aaaAA': 'AaaAA',
            'aaaaA': 'AaaaA',
            'aaaaa': 'Aaaaa',
            'aAaaa': 'AAaaa',
            'aAAaa': 'AAAaa',
            'aAAAA': 'AAAAA',
            '1AAAA': '1AAAA',
            '1A00A': '1A00A',
            '1A0#A': '1A0#A']

    def setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Unroll("calling upcase() with '#test' should return '#expected'")
    void "upcase - transforms the first char of #test to uppercase"() {
        when:
        def result = helper.upcase(test)

        then:
        result == expected

        where:
        test << fixture.keySet()
        expected << fixture.values()
    }

    @Unroll("calling upcase2() with '#test' should return '#expected'")
    void "upcase2 - transforms the first char of #test to uppercase"() {
        when:
        def result = helper.upcase2(test)

        then:
        result == expected

        where:
        test << fixture.keySet()
        expected << fixture.values()
    }

    @Unroll("calling formatLabel() with '#test' should return '#expected'")
    void "formatLabel - transforms the first char of #test to uppercase"() {
        when:
        def result = helper.formatLabel(test)

        then:
        result == expected

        where:
        test << fixture.keySet()
        expected << fixture.values()
    }
}
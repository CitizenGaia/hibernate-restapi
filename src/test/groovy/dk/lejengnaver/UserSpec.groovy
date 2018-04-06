package dk.lejengnaver

import grails.test.hibernate.HibernateSpec
import spock.lang.Shared

class UserSpec extends HibernateSpec {

    @Shared
    def TO_SHORT = 'TEST'
    @Shared
    def TO_LONG = TO_SHORT.multiply(16)

    def setup() {
    }

    def cleanup() {
    }

    void "test that a Player has a name and a location"() {
        expect:
        new User(username: username, password: password, location: location).validate() == shouldBeValid

        where:
        username  | password  | location  | shouldBeValid
        "unknown" | "unknown" | "nowhere" | true
        "unknown" | "unknown" | ""        | true
        "unknown" | "unknown" | null      | true
        "unknown" | TO_SHORT  | "nowhere" | false
        "unknown" | TO_LONG   | "nowhere" | false
        "unknown" | null      | "nowhere" | false
        null      | "unknown" | null      | false
    }
}

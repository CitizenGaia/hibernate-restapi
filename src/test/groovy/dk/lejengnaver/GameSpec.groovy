package dk.lejengnaver

import grails.test.hibernate.HibernateSpec
import spock.lang.Shared

import java.security.SecureRandom

class GameSpec extends HibernateSpec {

    @Shared
    def gameMap = new HashMap()
    @Shared
    def emptyGameMap = new HashMap()
    @Shared
    def random = new SecureRandom()

    def setup() {
        for (int i = 0; i < 3; i++) {
            String key = String.valueOf(random.nextInt(80) + 1)
            String value = String.valueOf(random.nextInt(9))
            gameMap[key] = value
        }
        new Game(title: "myTitle", author: "myAuthor", description: "myDescription", content: gameMap).save()
    }

    def cleanup() {
    }

    void "test that a Game has a name and a game"() {
        expect:
        new Game(title: title, author: author, description: description, content: content).validate() == shouldBeValid

        where:
        title          | author | description | content      | shouldBeValid
        "unclassified" | "test" | "none"      | gameMap      | true
        null           | "test" | "none"      | gameMap      | false
        "unclassified" | null   | "none"      | gameMap      | false
        "-"            | "test" | "none"      | gameMap      | false
        "unclassified" | "-"    | "none"      | gameMap      | false
        "unclassified" | "test" | ""          | gameMap      | true
        "unclassified" | "test" | null        | gameMap      | true
        "unclassified" | "test" | "none"      | emptyGameMap | true
        "unclassified" | "test" | "none"      | null         | false
    }



}

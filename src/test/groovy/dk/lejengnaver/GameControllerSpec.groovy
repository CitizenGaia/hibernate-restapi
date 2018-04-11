package dk.lejengnaver

import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class GameControllerSpec extends Specification {

    def setup() {
        Game.saveAll(
                new Game(title: 'Average classic', author: 'unknown', description: 'Average', content: new HashMap<String, String>('1':'2')),
                new Game(title: 'Easy classic', author: 'unknown', description: 'Below average', content: new HashMap<String, String>('3':'4', '5':'6')),
        )
    }

    @Transactional
    void 'test the get index action'() {
        when:
        RestBuilder restBuilder = new RestBuilder()
        RestResponse resp = restBuilder.get("http://localhost:${serverPort}/api/games")

        then:
        resp.status == 200
        resp.json.size() == 2
        resp.json.findAll { it.description == 'Average' && it.id == 1 }
        resp.json.findAll { it.title == 'Easy classic' && it.id == 2 }
        resp.json.findAll { it.author == 'unknown' }.size() == 2
    }

    @Transactional
    void 'test the get first'() {
        when:
        RestBuilder restBuilder = new RestBuilder()
        RestResponse resp = restBuilder.get("http://localhost:${serverPort}/api/games/1")

        then:
        resp.status == 200
        resp.json.description == 'Average'
        resp.json.author == 'unknown'
    }


}
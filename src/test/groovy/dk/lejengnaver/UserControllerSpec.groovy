package dk.lejengnaver

import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class UserControllerSpec extends Specification {

    def setup() {
        User.saveAll(
                new User(username: "Donald T", password: "changeme", location: "Tower"),
                new User(username: "Barack O", password: "changeme", location: "Polis"),
                new User(username: "George B Jr", password: "changeme", location: "Farm"),
                new User(username: "Bill C", password: "changeme", location: "Polis")
        )
    }

    @Transactional
    void 'test the get index action'() {
        when:
        RestBuilder restBuilder = new RestBuilder()
        RestResponse resp = restBuilder.get("http://localhost:${serverPort}/api/users")

        then:
        resp.status == 200
        resp.json.size() == 4
        resp.json.findAll { it.username == 'Donald T' && it.location == 'Tower' }
        resp.json.findAll { it.username == 'George B Jr' && it.location == 'Farm' }
        resp.json.findAll { it.location == 'Polis' }.size() == 2
    }

}
package dk.lejengnaver

import spock.lang.Specification
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification
import grails.transaction.Rollback

@Integration
@Rollback
class UserControllerSpec extends Specification {

    @Shared
    RestBuilder restBuilder = new RestBuilder()

    def setup() {
        User.saveAll(
                new User(username: "Donald T", password: "changeme", location: "Tower"),
                new User(username: "Barack O", password: "changeme", location: "Polis"),
                new User(username: "George B Jr", password: "changeme", location: "Farm"),
                new User(username: "Bill C", password: "changeme", location: "Polis"),
        )
    }

   void 'test the search action finds results'() {

       when:
       RestResponse resp = restBuilder.get("http://localhost:${serverPort}/api/users")

       then:
       resp.status == 200
/*
       resp.json.size() == 4
       resp.json.find { it.username == "Donald T" && it.location == 'Tower' }
       resp.json.find { it.username == "George B Jr" &&  it.name == 'Farm' }
       resp.json.find { it.location == "Polis" }
*/

    }

}
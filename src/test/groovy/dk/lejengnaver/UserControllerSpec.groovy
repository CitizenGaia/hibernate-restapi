package dk.lejengnaver

import grails.plugin.json.view.mvc.JsonViewResolver
import grails.test.hibernate.HibernateSpec
import grails.testing.web.controllers.ControllerUnitTest

class UserControllerSpec extends HibernateSpec implements ControllerUnitTest<UserController> {

    static doWithSpring = {
        jsonSmartViewResolver(JsonViewResolver)
    }

    void setup() {
        User.saveAll(
                new User(username: 'Adam Ant', password: "changeme", location: "All Sins"),
                new User(username: 'Brian Bow', password: "changeme", location: "Bell Grove"),
                new User(username: 'Carl Colt', password: "changeme", location: "Crank River"),
                new User(username: 'Danny Deal', password: "changeme", location: "Ocean Drive"),
        )
    }

    def cleanup() {
    }

    void 'test the create action'() {
        when: 'A query is executed that finds results by unique username'
        User[] users = User.findAll("from User as u where u.username=?", ['Adam Ant'])

        then: 'The response is correct'
        users.size() == 1
        users[0].location == 'All Sins'
    }

    void 'test the search action finds results'() {
        when: 'A query is executed that finds results by identical password'
        User[] users = User.findAll("from User as u where u.password=?", ['changeme'])

        then: 'The response is correct'
        users.size() == 4
    }

}
package dk.lejengnaver

import grails.rest.RestfulController

class UserController extends RestfulController {
    static responseFormats = ['json', 'xml']
    UserController() {
        super(User)
    }
}

package dk.lejengnaver

import grails.rest.RestfulController

class UserController extends RestfulController {

/*    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]*/

    static responseFormats = ['json', 'xml']
    UserController() {
        super(User)
    }
}

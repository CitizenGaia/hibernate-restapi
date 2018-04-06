package dk.lejengnaver

import grails.rest.RestfulController

class GameController extends RestfulController {
    static responseFormats = ['json', 'xml']
    GameController() {
        super(Game)
    }

    def search(String q, Integer max ) {
        if (q) {
            def query = Game.where {
                title ==~ "%${q}%"
            }
            respond query.list(max: Math.min( max ?: 10, 100))
        }
        else {
            respond([])
        }
    }

}

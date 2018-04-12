package hibernate.restapi

class UrlMappings {

    static mappings = {
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')

        "/api/games"(resources:"game")

        "/api/games"(resources:"game") {
            collection {
                '/search'(controller: 'game', action: 'search')
            }
        }

    }


}

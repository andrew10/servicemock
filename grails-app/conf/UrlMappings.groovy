class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "mock", action: "list")
        "/**"(controller: "mock", action: "renderResponse")
        "500"(view: '/error')
    }
}

package servicemock

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

class MockController {

    static scaffold = Mock

    static allowedMethods = [renderResponse: ['POST', 'DELETE', 'GET', 'PUT', 'DELETE', 'HEAD', 'TRACE', 'OPTIONS',
            'CONNECT', 'PATCH']]

    def index() {
        redirect(action: "list")
    }

    def list() {
        render(view: 'list', model: [mockInstanceList: Mock.list(params), mockInstanceTotal: Mock.count])
    }

    def create() {
        render(view: 'create')
    }

    def save() {
        def mockInstance = new Mock(params)
        if (!mockInstance.save(flush: true)) {
            return render(view: 'create', model: [mockInstance: mockInstance])
        }

        flash.success = "Mock has been successfully created"
        log.info("Mock has been successfully created")
        redirect(action: 'list')
    }

    def edit(Long id) {
        def mockInstance = Mock.get(id)
        if (!mockInstance) {
            flash.message = "Mock not found with id ${id}"
            return redirect(action: 'list')
        }

        render(view: 'edit', model: [mockInstance: mockInstance])
    }

    def update(Long id) {
        def mockInstance = Mock.get(id)
        if (!mockInstance) {
            flash.message = "Mock not found with id ${id}"
            return redirect(action: 'list')
        }

        mockInstance.properties = params
        if (!mockInstance.save(flush: true)) {
            return render(view: 'edit', model: [mockInstance: mockInstance])
        }

        flash.success = "Mock Response has been successfully updated"
        log.info("Mock has been successfully updated")
        redirect(action: 'list')
    }

    def delete(Long id) {
        def mock = Mock.get(id)

        if (!mock) {
            flash.message = "Mock not found with id ${id}"
            return redirect(action: 'list')
        }
        try {
            mock.delete(flush: true)
            flash.success = "Mock Response has been successfully deleted"
        } catch (Exception e) {
            log.warn("Failed to delete mock :: id: ${mock.id}. Exception:: ${e.message}")
            flash.error = "Failed to delete this Mock"
        }

        redirect action: 'list'
    }

    def renderResponse() {
        def mockURL = request.forwardURI
        def method = RequestMethod.valueOf(request.method)

        if (request.queryString) {
            mockURL = mockURL + "?${request.queryString}"
        }

        def mock = Mock.findByUrlAndMethod(mockURL, method)
        if (!mock) {
            return render(status: HttpServletResponse.SC_NOT_FOUND)
        }

        def response = mock.response
        response ? render(response) as JSON : render(status: HttpServletResponse.SC_OK)
    }
}

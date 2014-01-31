package servicemock

import grails.converters.JSON
import org.codehaus.groovy.grails.validation.routines.UrlValidator

class MockController {

    static scaffold = Mock

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
        def method = request.method
        if (request.queryString) {
            mockURL = mockURL + "?${request.queryString}"
        }

        def mock = Mock.findByUrl(mockURL)
        if (!mock || mock.method.toString() != method) {
            return render(status: 404)
        }

        render(mock.response) as JSON
    }
}

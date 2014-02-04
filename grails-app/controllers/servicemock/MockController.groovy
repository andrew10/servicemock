package servicemock

import grails.converters.JSON
import grails.transaction.Transactional

import javax.servlet.http.HttpServletResponse

@Transactional(readOnly = true)
class MockController {

    static allowedMethods = [renderResponse: ['POST', 'DELETE', 'GET', 'PUT', 'DELETE', 'HEAD', 'OPTIONS', 'PATCH']]

    def index() {
        redirect(action: "list")
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render(view: 'list', model: [mockInstanceList: Mock.list(params), mockInstanceTotal: Mock.count])
    }

    def create() {
        render(view: 'create')
    }

    @Transactional
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

    @Transactional
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

    @Transactional
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

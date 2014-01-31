package servicemock

class UtilsTagLib {

    static namespace = "u"

    def alert = { attrs ->

        if (flash.success ?: attrs.success) {
            out << "<div class=\"alert alert-success\">${flash.remove('success') ?: attrs.remove('success')}</div>"
        }

        if (flash.message ?: attrs.message) {
            out << "<div class=\"alert alert-info\">${flash.remove('message') ?: attrs.remove('message')}</div>"
        }

        if (flash.error ?: attrs.error) {
            out << "<div class=\"alert alert-error\">${flash.remove('error') ?: attrs.remove('error')}</div>"
        }
    }

    /**
     * Truncates large string.
     */
    def slasher = { attrs ->
        if (!attrs.str) throw new IllegalArgumentException("Attribute [str] should be specified for the tag [u:slasher]")
        if (!attrs.limit) throw new IllegalArgumentException("Attribute [limit] should be specified for the tag [u:slasher]")

        String str = attrs.remove('str')
        int limit = attrs.int('limit')
        out << "${str.size() < limit ? str : str.substring(0, limit) + '...'}"
    }

    /**
     * Renders confirmation modal window.
     *
     * @attr id required. Modal Id
     * @attr title required. Modal window title
     * @attr message required. Modal window Message
     * @attr cancelLabel optional. Cancel button label
     * @attr confirmLabel optional. Confirmation button label
     * @attr action required. Confirmation submit action
     * @attr controller required. Confirmation submit controller
     * @params parameters
     */
    def confirmationWindow = { attrs, body ->
        def id = attrs.remove('id')
        if (!id) {
            throw new IllegalArgumentException("Attribute [id] should be specified for the tag [u:confirmationWindow]")
        }
        def title = attrs.remove('title')
        if (!title) {
            throw new IllegalArgumentException("Attribute [title] should be specified for the tag [u:confirmationWindow]")
        }
        def message = attrs.remove('message')
        if (!message) {
            throw new IllegalArgumentException("Attribute [message] should be specified for the tag [u:confirmationWindow]")
        }
        def action = attrs.remove('action')
        if (!action) {
            throw new IllegalArgumentException("Attribute [action] should be specified for the tag [u:confirmationWindow]")
        }
        def controller = attrs.remove('controller')
        if (!controller) {
            throw new IllegalArgumentException("Attribute [controller] should be specified for the tag [u:confirmationWindow]")
        }
        def params = attrs.remove('params')
        def cancelLabel = attrs.remove('cancelLabel') ?: 'Cancel'
        def confirmLabel = attrs.remove('confirmLabel') ?: 'Ok'

        out << render(template: "/taglib/confirmationWindow", model: [id: id, title: title, message: message,
                action: action, controller: controller, body: body(), cancelLabel: cancelLabel,
                confirmLabel: confirmLabel, params: params])
    }
}

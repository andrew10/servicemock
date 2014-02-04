import javax.servlet.http.HttpServletRequest

class BootStrap {

    def init = { servletContext ->
        HttpServletRequest.metaClass.getHostUrl = {
            def port = delegate.serverPort
            def portStr = ((port == 80) || (port == 443)) ? "" : ":${port}"
            return (delegate.scheme + "://" + delegate.serverName + portStr)
        }
    }
    def destroy = {
    }
}

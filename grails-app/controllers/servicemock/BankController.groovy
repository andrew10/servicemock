package servicemock

import grails.converters.JSON
import groovy.json.JsonSlurper

class BankController {
    
    def responseService

    def index() { }
    
    def mockResponse(String path){
        def responseFile = new File(path)
        if(!responseFile.exists()){
            log.info("Can't find file for response")
            return render(status: 404)
        }
        render new JsonSlurper().parseText(responseFile.text) as JSON
    }
    
    def mock(){
        println(params)
    }
}

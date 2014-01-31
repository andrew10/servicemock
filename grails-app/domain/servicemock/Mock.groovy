package servicemock

class Mock {

    String url
    String response
    RequestMethod method = RequestMethod.GET

    static constraints = {
        url blank: false, nullable: false, unique: true, validator: { val, obj ->
            if (obj.method == RequestMethod.POST) {
                return val.contains('?') ? ['mock.incorrect.post.request.error'] : true
            }
        }
        response blank: false, nullable: false
    }

    static mapping = {
        response(sqlType: 'text')
    }
}

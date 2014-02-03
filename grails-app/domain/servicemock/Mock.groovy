package servicemock

class Mock {

    String url
    String response
    RequestMethod method

    static constraints = {
        url blank: false, nullable: false, unique: ['method'], validator: { val, obj ->
            (obj?.method == RequestMethod.POST) && val.contains('?') ? ['mock.incorrect.post.request.error'] : true
        }
        response blank: true, nullable: true, validator: { val, obj ->
            val && (obj?.method == RequestMethod.HEAD) ? ['mock.incorrect.head.request.error'] : true
        }
        method blank: false, nullable: false
    }

    static mapping = {
        response(sqlType: 'text')
    }
}

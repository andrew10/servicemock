modules = {
    application {
        dependsOn 'bootstrap, bootstrap-switch'
        resource url: 'js/application.js'

        resource url: '/less/application.less', attrs: [rel: 'stylesheet/less', type: 'css', media: 'all'], disposition: 'head'
    }

    'bootstrap-switch' {
        dependsOn 'bootstrap-js, bootstrap-less'

        resource url: '/less/deps/mixins.less', attrs: [rel: 'stylesheet/less', type: 'css', media: 'all'], disposition: 'head'
        resource url: '/less/deps/variables.less', attrs: [rel: 'stylesheet/less', type: 'css', media: 'all'], disposition: 'head'
        resource url: '/less/bootstrapSwitch.less', attrs: [rel: 'stylesheet/less', type: 'css', media: 'all'], disposition: 'head'
        resource url: '/js/bootstrapSwitch.js'
    }
}
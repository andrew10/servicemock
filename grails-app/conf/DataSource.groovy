import org.hibernate.dialect.MySQL5InnoDBDialect

dataSource {
    pooled = true
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
}

// environment specific settings
environments {
    development {
        dataSource{
            dbCreate = "update"
            driverClassName = 'com.mysql.jdbc.Driver'
            dialect = MySQL5InnoDBDialect
            url = "jdbc:mysql://localhost:3306/mockservice?autoReconnect=true&useUnicode=true&characterEncoding=utf8"
            username = "root"
            password = "qwerty10"
        }
    }

    local {
        dataSource{
            dbCreate = "update"
            driverClassName = 'com.mysql.jdbc.Driver'
            dialect = MySQL5InnoDBDialect
            url = "jdbc:mysql://localhost:3306/m_service_db?autoReconnect=true&useUnicode=true&characterEncoding=utf8"
            username = "m_service_user"
            password = "qwerty10"
        }
    }


    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }

    //OpenShfit web hosting settings
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = 'com.mysql.jdbc.Driver'
            dialect = MySQL5InnoDBDialect

            String host = System.getenv('OPENSHIFT_MYSQL_DB_HOST')
            String port = System.getenv('OPENSHIFT_MYSQL_DB_PORT')
            String dbName = System.getenv('OPENSHIFT_APP_NAME')
            url = "jdbc:mysql://$host:$port/$dbName"
//            username = "adminmG49s2n"
//            password = "mKWD2UvNfg1F"
            username = System.env.OPENSHIFT_POSTGRESQL_DB_USERNAME
            password = System.env.OPENSHIFT_POSTGRESQL_DB_PASSWORD
        }
    }
}

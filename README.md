#servicemock


##Requirements

To run *servicemock* you need JDK 6 or later and grails-2.3.5. Installation process described in
detail in official docs: <a href="http://grails.org/doc/latest/guide/gettingStarted.html#requirements" target="_blank">
grails </a>, <a href="http://docs.oracle.com/javase/7/docs/webnotes/install/windows/jdk-installation-windows.html"
target="_blank">JDK</a>

##Run locally

###Database configuration

* Download and install *MySQL 5.6 db-server* (<a href="http://dev.mysql.com/doc/refman/5.1/en/windows-installation.html"
    target="_blank">official guide</a>);
* Create database *m_service_db* and user *m_service_user* (passsword = *qwerty10*); This can be done in this way:
    - open mysql console and create a database:
        
        ```sh
        mysql> create database m_service_db; 
        Query OK, 1 row affected (0.00 sec)

    - create new user:
        
        ```sh
        mysql> grant usage on *.* to m_service_user@localhost identified by 'qwerty10';
        Query OK, 0 rows affected (0.00 sec)

    - grant all privileges on the *m_service_db* to this user:

        ```sh
        mysql> grant all privileges on m_service_db.* to m_service_user@localhost; 
        Query OK, 0 rows affected (0.00 sec)

###Run *servicemoock*

Go to the application directory and run this grails command:

    grails local -Dserver.port=8099 run-app

After that you can view *servicemock* by pointing a web browser to http://localhost:8099.

##Deploy application to *OpenShift* web-hosting

Before we deploy the application to OpenShift, we'll have to do few setup tasks:

1) Sign up for an OpenShift Account. It is completely free and Red Hat gives every user three free *Gears* on which to run your applications.

2) Install the *rhc client* tool on your machine. rhc is installable as a ruby gem, so you need to have ruby 1.8.7 or above on your machine. To install rhc, just type:

        gem install rhc

For additional assistance setting up the rhc command-line tool, see the following page:
<a href="https://openshift.redhat.com/community/developers/rhc-client-tools-install" target="_blank">
rhc-client-tools-install
</a>

3) Setup your OpenShift account using `rhc setup` command. This command will help you create you own unique OpenShift domain and upload your ssh keys to OpenShift server.

4) After setup, we can create OpenShift application by running the following command:

    rhc create-app servicemock tomcat-7 mysql-5.1

This will create an application container, called a *gear*. OpenShift will also setup a private git repository and clone the repository to the local system. Finally, OpenShift will propagate the DNS to the outside world.
The application will be accessible at http://mockservice-{domain-name}.rhcloud.com *replace domain-name with your own unique OpenShift domain name*.

5) As we will be deploying war file, so we have to delete the default source generated by OpenShift:

    $ git rm -rf src/ pom.xml
    $ git commit -am "deleted default source code"

6) At final we have to add some changes to default *server.xml* generated by OpenShift to avoid problems with css styles. More about it problem
<a href="https://www.openshift.com/forums/openshift/missing-css-files-when-using-grails-war-modules" target="_blank">here</a>.

Open OpenShift mockservice/.openshift/config/server.xml and set unpackWars to true ( in the Host element ):

    <Host name="localhost"  appBase="webapps" unpackWARs="true" autoDeploy="true">    


Well, Now we can deploy application: 

- Go to application directory and package *servicemock* into a war file:

    `grails prod target/ROOT.war`

- Copy ROOT.war to your OpenShift mockservice application webapps folder.

- Add the war to your git repository and push the changes:
    
    ```git
    $ git add .
    $ git commit -am "Deploy mockservice"
    $ git push

After code is pushed and war is successfully deployed, we can view the application running at
http://mockservice-{domain-name}.rhcloud.com.

A functional demo of the resulting project is available for review
<a href="http://mockservice-servicemock.rhcloud.com">here</a>.

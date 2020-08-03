# Tomcat Dockerfiles

## Motivation

Why not use the standard Dockerhub Tomcat image [https://hub.docker.com/_/tomcat/]()?

It's a perfectly fine image, but has two issues:

Firstly it's fairly big in size - the 8.0-jre8 tag has a size of 558MB, whilst it's based on a debian image that start's life at around 100MB. That's quite a big increase.

Secondly it runs as the root user which is a security risk. Better would be to run as a dedicated non-privileged user, but better still to allow to run as an arbitrarily assigned user ID as is preferred by OpenShift. This image supports both approaches.

So this series of images try to be as lightweight as possible and allow to run as an arbitrarily assigned user ID (though a tomcat user with user ID of 501 is defined as the default user). The result for the 8.5-jre8 tag is an image of size 285MB, approx half that of the DockerHub tomcat image, and not bad as that includes the Java JRE.
 
## Usage

Typical basic usage:
```
docker run -d -p 8080:8080 -v /path/to/your/application.war:/usr/local/tomcat/webapps/ROOT.war informaticsmatters/tomcat:8.5-jre8 
```

More normally though you would create your own Dockerfile that places the contents into the /usr/local/tomcat/webapps directory.

## Notes

The trick to running as an arbitrarily assigned user ID is that this user belongs to the root group (ID 0) so the permissions under /usr/local/tomcat are set to be readable/writable as appropritate by that group (though still owned by root). This allows the arbitrarily assigned user to access the files as necessary. If you hit permission problems you probably need to do a `chown -R tomcat:0` on the offending files.

Jan 2019: The build was switched to use Tomcat 8.5 as 8.0 is no longer supported and downloads are no longer available.
The older informaticsmatters/tomcat:8.0-jre8 images are still present on Docker Hub.

Java8 is not longer available from the Debian 'Buster' repositories so only Java11 versions can now be built.

## Alternative Docker files

The core Dockerfile have names like Dockerfile-tomcat80-jre8. 
An alternative Dockerfile that leaves the default Tomcat apps in place is also present and can be used.

### Keycloak

The [Dockerfile-tomcat80-jre8-keycloak]() Dockerfile can be used to create a Tomcat image containing the Keycloak OIDC adapters.
These are pushed to Docker Hub using an image name of `informaticsmatters/tomcat-keycloak` and can be used to secure your
application against Keycloak (Red Hat SSO). You can use the Dockerfile to build your own image for a different version of
Keycloak. See comments in the Dockerfile for further details about customisation and use.

To use this image you will need to at least:

1. provide a suitable `WEB-INF/keycloak.json` file in your war file.
1. modify `WEB-INF/web.xml` to add suitable `security-constraint`, `login-config` and `security-role` elements.
1. modify `META-INF/context.xml` to include the Keycloak valve.

For an example see ...

## Source code and image locations

Source code can be found on GitHub: https://github.com/InformaticsMatters/docker-tomcat

Docker images are automatically built on Docker Hub: https://hub.docker.com/r/informaticsmatters/tomcat/


# Tomcat Dockerfiles

## Motivation

Why not use the standard Dockerhub Tomcat image [](https://hub.docker.com/_/tomcat/)?

It's a perfectly fine image, but has two issues:

Firtly it's fairly big in size - the 8.0-jre8 tag has a size of 558MB, whilst it's based on a debian image that start's life at around 100MB. That's quite a big increase.

Secondly it runs as the root user which is a security risk. Better would be to run as a dedicated non-priviledged user, but better still to allow to run as an arbitarily assigned user ID as is preferred by OpenShift. Thiss image supports both approaches.

So this series of images try to be as lightweight as possible and allow to run as an arbitarily assigned user ID (though a tomcat user with user ID of 1000 is defined as the default user). The result for the 8.0-jre8 tag is an image of size 285MB, approx half that of the DockerHub tomcat image, and not bad as that includes the Java JRE.
 
## Usage

Typical basic usage:
```
docker run -d -p 8080:8080 -v /path/to/your/application.war:/usr/local/tomcat/webapps/ROOT.war informaticsmatters/tomcat:8.0-jre8 
```

More normally though you would create your own Dockerfile that places the contents into the /usr/local/tomcat/webapps directory.

## Notes

The trick to running as an arbitarily assigned user ID is that this user belongs to the root group (ID 0) so the permissions under /usr/local/tomcat are set to be readable/writable as appropritate by that group (though still owned by root). This allows the arbitarily assigned user to access the files as necessary. If you hit permission problems you probably need to do a `chown -R root:0` on the offending files.  

## Alternative Docker files

The core Dockerfile have names like Dockerfile-tomcat80-jre8. These build a minimal image with no content in the webapps directory. It also does not do any verifications on the tomcat download file which you might not find acceptable. Alternative Dockerfiles that do verificationss (but at the cost of extra dependencies and thus increased size) or that leave the default Tomcat apps in place) are also present and can be used.

## Docker Hub images

The core minimal images are autmatically built on Docker Hub and can be found [here](https://hub.docker.com/r/informaticsmatters/tomcat/).


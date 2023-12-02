package ru.sberbank.edu;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8088);

        StandardContext context = (StandardContext) tomcat.addWebapp("", new File("module08/src/main/webapp").getAbsolutePath());

        File webInfClasses = new File("module08/target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", webInfClasses.getAbsolutePath(), "/"));
        context.setResources(resources);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}
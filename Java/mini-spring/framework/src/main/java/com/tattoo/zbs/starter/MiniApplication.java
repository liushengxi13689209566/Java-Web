package com.tattoo.zbs.starter;

import com.tattoo.zbs.core.ClassScanner;
import com.tattoo.zbs.web.server.TomcatServer;
import org.apache.catalina.LifecycleException;

import java.io.IOException;
import java.util.List;

public class MiniApplication {
    public static void run(Class<?> cls, String[] args) {
        System.out.println("Hello Mini Spring !!!");
        TomcatServer tomcatServer = new TomcatServer(args);

        try {
            tomcatServer.startServer();
            List<Class<?>> classList = ClassScanner.scanClasses(
                    cls.getPackage().getName());
            classList.forEach(it -> System.out.println(it.getName()));

        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

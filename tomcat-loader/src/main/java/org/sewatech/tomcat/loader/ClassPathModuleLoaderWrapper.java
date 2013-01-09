package org.sewatech.tomcat.loader;

import org.apache.catalina.LifecycleException;
import org.jboss.modules.*;

import java.lang.reflect.Constructor;

public class ClassPathModuleLoaderWrapper {

    private ModuleLoader realLoader;

    public ClassPathModuleLoaderWrapper(LocalModuleLoader loader, String mainClass, String classPath, String dependencies) throws LifecycleException {
        try {
            Class<?> clazz = Class.forName("org.jboss.modules.ClassPathModuleLoader");
            Constructor<?> defaultConstructor = clazz.getDeclaredConstructor(ModuleLoader.class, String.class, String.class, String.class);
            defaultConstructor.setAccessible(true);
            realLoader = (ModuleLoader) defaultConstructor.newInstance(loader, mainClass, classPath, dependencies);
        } catch (Exception e) {
            throw new LifecycleException(e);
        }

    }

    public Module loadModule(ModuleIdentifier identifier) throws ModuleLoadException {
        return realLoader.loadModule(identifier);
    }
}

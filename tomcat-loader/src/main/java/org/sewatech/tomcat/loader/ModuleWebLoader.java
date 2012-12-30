package org.sewatech.tomcat.loader;

import org.jboss.modules.Module;
import org.jboss.modules.ModuleIdentifier;

import org.apache.catalina.*;
import org.apache.catalina.mbeans.MBeanUtils;
import org.apache.catalina.util.LifecycleMBeanBase;

import javax.servlet.ServletContext;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class ModuleWebLoader extends LifecycleMBeanBase implements Loader {

    private static final String MODULES_ROOT = "/Volumes/MacHDD/ProjetBis/tomcat/modules-repository";
    public static final String MODULE_NAME = "org.sewatech.module.app-module";

    public ModuleWebLoader() {
        this(null);
    }

    public ModuleWebLoader(ClassLoader parent) {
        super();
    }

    private ClassLoader classLoader = null;
    private Container container = null;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public Container getContainer() {
        return (container);
    }
    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public boolean getDelegate() {
        return false;
    }
    @Override
    public void setDelegate(boolean delegate) {
    }


    @Override
    public String getInfo() {
        return "ModuleWebLoader/1.0";
    }

    @Override
    public boolean getReloadable() {
        return false;
    }
    @Override
    public void setReloadable(boolean reloadable) {
    }


    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void addRepository(String repository) {
    }

    @Override
    public void backgroundProcess() {
    }

    @Override
    public String[] findRepositories() {
        return new String[0];
    }

    @Override
    public boolean modified() {
        return false ;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("ModuleWebLoader[");
        if (container != null)
            sb.append(container.getName());
        sb.append("]");
        return (sb.toString());

    }


    @Override
    protected void startInternal() throws LifecycleException {

        if (container.getResources() == null) {
            setState(LifecycleState.STARTING);
            return;
        }

        // Construct a class loader based on our current repositories list
        try {
            MultiRootsModuleLoader loader = new MultiRootsModuleLoader(new File[]{new File(MODULES_ROOT)});
            ModuleIdentifier identifier = ModuleIdentifier.create(MODULE_NAME);
            Module module = loader.loadModule(identifier);

            classLoader = module.getClassLoader();
        } catch (Throwable t) {
            throw new LifecycleException("start: ", t);
        }

        setState(LifecycleState.STARTING);
    }

    @Override
    protected void stopInternal() throws LifecycleException {
        setState(LifecycleState.STOPPING);

        // Remove context attributes as appropriate
        if (container instanceof Context) {
            ServletContext servletContext = ((Context) container).getServletContext();
            servletContext.removeAttribute(Globals.CLASS_PATH_ATTR);
        }

        classLoader = null;
    }


    @Override
    protected String getDomainInternal() {
        return MBeanUtils.getDomain(container);
    }


    @Override
    protected String getObjectNameKeyProperties() {

        StringBuilder name = new StringBuilder("type=Loader");

        if (container instanceof Context) {
            name.append(",context=");
            Context context = (Context) container;

            String contextName = context.getName();
            if (!contextName.startsWith("/")) {
                name.append("/");
            }
            name.append(contextName);

            name.append(",host=");
            name.append(context.getParent().getName());
        } else {
            // Unlikely / impossible? Handle it to be safe
            name.append(",container=");
            name.append(container.getName());
        }

        return name.toString();
    }

}

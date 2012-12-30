package org.sewatech.tomcat.loader;

import org.jboss.modules.LocalModuleFinder;
import org.jboss.modules.ModuleFinder;
import org.jboss.modules.ModuleLoader;
import org.jboss.modules.filter.PathFilters;

import java.io.File;

public class MultiRootsModuleLoader extends ModuleLoader {

    public MultiRootsModuleLoader(final File[] repoRoots) {
        super(new ModuleFinder[] { new LocalModuleFinder(repoRoots, PathFilters.acceptAll())});
    }
}

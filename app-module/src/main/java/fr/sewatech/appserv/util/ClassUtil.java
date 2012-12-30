package fr.sewatech.appserv.util;

import java.io.File;
import java.net.URL;

public final class ClassUtil {
	public static File getLibrary(Class<?> clazz) {
		String name = clazz.getName();
		name = name.replace('.', '/');
		name += ".class";
		
		ClassLoader classLoader = clazz.getClassLoader();

		URL location = classLoader.getResource(name);
        String locationText;
        if (location == null) {
            return null;
        } else {
            locationText = location.getFile();
            locationText = locationText.replace("file:", "");
            String catalinaHome = System.getenv("CATALINA_HOME");
            if (catalinaHome != null) {
                locationText = locationText.replace(catalinaHome, "$CATALINA_HOME");
                locationText = locationText.replace("/Volumes/MacHDD/ProjetBis/conference/jbmd-prez/demo/cl-demo", "$PROJECT_HOME");
            }

            return new File(locationText.substring(0, locationText.indexOf(name)-1));
        }
    }

}

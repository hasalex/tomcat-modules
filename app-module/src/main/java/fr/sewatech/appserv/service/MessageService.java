package fr.sewatech.appserv.service;

import fr.sewatech.appserv.util.ClassUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MessageService {

	private final File origin;

	public MessageService() {
        origin = ClassUtil.getLibrary(MessageService.class);
    }
	
	public String getMessage() {
        String message = "Message from " + origin;
        message += " with classloader " + MessageService.class.getClassLoader().getClass();
        return message;
	}
}

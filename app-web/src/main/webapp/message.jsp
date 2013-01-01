<%@ page import="fr.sewatech.appserv.service.MessageService" %>
<!DOCTYPE html>
<html>
<head>
    <title>Message via JSP</title>
</head>
<body>
<%
    MessageService service = new MessageService();

    String message = service.getMessage();
    ClassLoader classLoader = page.getClass().getClassLoader();
%>
<p><%= message %> </p>
<p>ClassLoader Hierarchy</p>
<ul>
    <%
        while (classLoader != null) {
    %>
    <li><%= classLoader.getClass() %></li>
    <%
            classLoader = classLoader.getParent();
        }
    %>
</ul>
</body>
</html>

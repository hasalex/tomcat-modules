<%@ page import="fr.sewatech.appserv.util.ClassUtil" %>
<!DOCTYPE html>
<html>
<head>
    <title>Message via JSP</title>
</head>
<body>
<p>JSP loaded from <%= ClassUtil.getLibrary(this)%> with the classloader <%= ClassUtil.getClassLoader(this) %> </p>
<p>ClassLoader Hierarchy</p>
<ul>
    <%
        ClassLoader classLoader = page.getClass().getClassLoader();
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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionLoginServlet extends HttpServlet {

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    try {
      // load the driver
      Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    } catch (ClassNotFoundException e) {
      throw new UnavailableException(
          "Login init() ClassNotFoundException: " + e.getMessage());
    } catch (IllegalAccessException e) {
      throw new UnavailableException(
          "Login init() IllegalAccessException: " + e.getMessage());
    } catch (InstantiationException e) {
      throw new UnavailableException(
          "Login init() InstantiationException: " + e.getMessage());
    }
  }
@WebServlet("/loginSession")
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Login</title>");
    out.println("</head>");
    out.println("<body>");

    HttpSession session = request.getSession();
    SessionConnection sessionConnection = (SessionConnection) session
        .getAttribute("sessionconnection");
    Connection connection = null;
    if (sessionConnection != null) {
      connection = sessionConnection.getConnection();
    }
    if (connection == null) {
      String userName = request.getParameter("username");
      String password = request.getParameter("password");
      if (userName == null || password == null) {
        // prompt the user for her username and password
        out.println("<form method=\"get\" action=\"SessionLogin\">");
        out.println("Please specify the following to log in:<p>");
        out.println("Username: <input type=\"text\" "
            + "name=\"username\" size=\"30\"><p>");
        out.println("Password: <input type=\"password\" "
            + "name=\"password\" size=\"30\"><p>");
        out.println("<input type=\"submit\" value=\"Login\">");
        out.println("</form>");
      } else {
        // create the connection
        try {
          connection = DriverManager.getConnection(
              "jdbc:oracle:thin:@dssw2k01:1521:orcl", userName,
              password);
        } catch (SQLException e) {
          out.println("Login doGet() " + e.getMessage());
        }
        if (connection != null) {
          // store the connection
          sessionConnection = new SessionConnection();
          sessionConnection.setConnection(connection);
          session
              .setAttribute("sessionconnection",
                  sessionConnection);
          response.sendRedirect("SessionLogin");
          return;
        }
      }
    } else {
      String logout = request.getParameter("logout");
      if (logout == null) {
        // test the connection
        Statement statement = null;
        ResultSet resultSet = null;
        String userName = null;
        try {
          statement = connection.createStatement();
          resultSet = statement
              .executeQuery("select initcap(user) from sys.dual");
          if (resultSet.next())
            userName = resultSet.getString(1);
        } catch (SQLException e) {
          out.println("Login doGet() SQLException: " + e.getMessage()
              + "<p>");
        } finally {
          if (resultSet != null)
            try {
              resultSet.close();
            } catch (SQLException ignore) {
            }
          if (statement != null)
            try {
              statement.close();
            } catch (SQLException ignore) {
            }
        }
        out.println("Hello " + userName + "!<p>");
        out.println("Your session ID is " + session.getId() + "<p>");
        out
            .println("It was created on "
                + new java.util.Date(session.getCreationTime())
                + "<p>");
        out.println("It was last accessed on "
            + new java.util.Date(session.getLastAccessedTime())
            + "<p>");
        out.println("<form method=\"get\" action=\"SessionLogin\">");
        out.println("<input type=\"submit\" name=\"logout\" "
            + "value=\"Logout\">");
        out.println("</form>");
      } else {
        // close the connection and remove it from the session
        try {
          connection.close();
        } catch (SQLException ignore) {
        }
        session.removeAttribute("sessionconnection");
        out.println("You have been logged out.");
      }
    }
    out.println("</body>");
    out.println("</html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    doGet(request, response);
  }
}

class SessionConnection implements HttpSessionBindingListener {

  Connection connection;

  public SessionConnection() {
    connection = null;
  }

  public SessionConnection(Connection connection) {
    this.connection = connection;
  }

  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public void valueBound(HttpSessionBindingEvent event) {
    if (connection != null) {
      System.out.println("Binding a valid connection");
    } else {
      System.out.println("Binding a null connection");
    }
  }

  public void valueUnbound(HttpSessionBindingEvent event) {
    if (connection != null) {
      System.out
          .println("Closing the bound connection as the session expires");
      try {
        connection.close();
      } catch (SQLException ignore) {
      }
    }
  }
}
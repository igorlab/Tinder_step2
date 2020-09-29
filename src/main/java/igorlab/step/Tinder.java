package igorlab.step;

import igorlab.step.service.DBservice;
import igorlab.step.servlet.AnyFileFolderServlet;
import igorlab.step.servlet.LikedUsers;
import igorlab.step.servlet.Login;
import igorlab.step.servlet.Users;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.stream.Stream;

public class Tinder {
    public static void main(String[] args) throws Exception {


        DBservice psqlStorage = new DBservice();

        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(
                new LikedUsers(psqlStorage)), "/liked");

        handler.addServlet(new ServletHolder(
                new Users(psqlStorage)), "/users");


        ServletHolder anyFile = new ServletHolder(new AnyFileFolderServlet("assets"));
        handler.addServlet(anyFile, "/css/*");

        handler.addServlet(new ServletHolder(
                new Login(psqlStorage)), "/");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}

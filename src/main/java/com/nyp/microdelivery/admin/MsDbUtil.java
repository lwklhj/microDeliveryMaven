package com.nyp.microdelivery.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MsDbUtil {
    private static MsDbUtil instance;
    private static DataSource dataSource;
    //private static String jndiName = "jdbc/SQLite";
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static List<UserSearch> getAllUsers(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        String sql = "select * from user order by userName";

        List<UserSearch> list = session.createNativeQuery(sql).addEntity(UserSearch.class).list();




        session.close();
        return list;

    }

    public static void save(UserSearch userSearch){

        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(userSearch);
        session.getTransaction().commit();
        session.close();
    }


        /*public static MsDbUtil getInstance() throws Exception {
            if (instance == null) {
                instance = new MsDbUtil();
            }
                return instance;
        }

        private MsDbUtil() throws Exception {
            dataSource = getDataSource();
        }

        private DataSource getDataSource() throws NamingException {
            Context context = new InitialContext();

            DataSource theDataSource = (DataSource) context.lookup(jndiName);

            return theDataSource;
        }*/

        public static List<UserSearch> getEnabledUsers(int disable)  {
            Session session=sessionFactory.openSession();
            session.beginTransaction();
            String sql ="";
            if(disable==0){
                sql = "select * from user where disable = 0 or disable is NULL order by userName";
            }else{
                sql = "select * from user where disable = 1 or disable is NULL order by userName";
            }



            List<UserSearch> list = session.createNativeQuery(sql).addEntity(UserSearch.class).list();

            session.close();
            return list;



            /*List<UserSearch> users = new ArrayList<>();

            Connection myConn = null;
            Statement myStmt = null;
            ResultSet myRs = null;

            try {
                myConn = getConnection();

                String sql = "select * from user where disable != 1 or disable is NULL order by userName";

                myStmt = myConn.createStatement();

                myRs = myStmt.executeQuery(sql);

                // process result set
                while (myRs.next()) {

                    // retrieve data from result set row
                    String username = myRs.getString("userName");
                    String password = myRs.getString("password");
                    String email = myRs.getString("email");
                    String addr = myRs.getString("addr");
                    String contact = myRs.getString("phoneNo");
                    Integer disable = myRs.getInt("disable");

                    // create new user object
                    UserSearch tempUser = new UserSearch(username, password, email, addr, contact, disable);

                    // add it to the list of users
                    users.add(tempUser);
                }

               // System.out.println("*************** => After getUsers while"+ users.size());
                return users;
            }
            finally {
                close (myConn, myStmt, myRs);
            }*/
        }

    //DISABLE USER
    public static void disableUser(UserSearch user){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        user.setDisable(1);
        session.update(user);
        session.getTransaction().commit();
        session.close();

        /*Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = getConnection();

            String sql = "update user set disable=1 where userName =?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1,user.getUsername());
            myStmt.executeUpdate();
            myConn.commit();

            // set params


            myStmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close (myConn, myStmt);
        }*/

    }

    //ENABLE USER
    public static void enableUser(UserSearch user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        user.setDisable(0);
        session.update(user);
        session.getTransaction().commit();
        session.close();

        /*Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = getConnection();

            String sql = "update user set disable=0 where userName =?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1,user.getUsername());
            myStmt.executeUpdate();
            myConn.commit();

            myStmt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close (myConn, myStmt);
        }*/

    }

        private static Connection getConnection() throws SQLException {

            Connection theConn = dataSource.getConnection();

            return theConn;
        }

        private static void close(Connection theConn, Statement theStmt) {
            close(theConn, theStmt, null);
        }

        private static void close(Connection theConn, Statement theStmt, ResultSet theRs) {

            try {
                if (theRs != null) {
                    theRs.close();
                }

                if (theStmt != null) {
                    theStmt.close();
                }

                if (theConn != null) {
                    theConn.close();
                }

            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        public List<UserSearch> searchUsers(String theSearchUser)  throws Exception {
            Session session=sessionFactory.openSession();
            session.beginTransaction();
            String sql = "select * from user where lower(userName) like ?";


            List<UserSearch> list = session.createNativeQuery(sql).addEntity(UserSearch.class).list();

            session.close();
            return list;

            /*List<UserSearch> users = new ArrayList<>();

            Connection myConn = null;
            PreparedStatement myStmt = null;
            ResultSet myRs = null;

            try {

                // get connection to database
                myConn = dataSource.getConnection();

                //
                // only search by name if theSearchName is not empty
                //
                if (theSearchUser != null && theSearchUser.trim().length() > 0) {

                    // create sql to search for students by name
                    String sql = "select * from user where lower(userName) like ?";

                    // create prepared statement
                    myStmt = myConn.prepareStatement(sql);

                    // set params
                    String theSearchUserLike = "%" + theSearchUser.toLowerCase() + "%";
                    myStmt.setString(1, theSearchUserLike);

                } else {
                    // create sql to get all students
                    String sql = "select * from user order by userName";

                    // create prepared statement
                    myStmt = myConn.prepareStatement(sql);
                }

                // execute statement
                myRs = myStmt.executeQuery();

                // retrieve data from result set row
                while (myRs.next()) {

                    // retrieve data from result set row
                    String un = myRs.getString("username");
                    String password = myRs.getString("password");
                    String email = myRs.getString("email");
                    String address = myRs.getString("address");
                    String phoneNo = myRs.getString("contact");
                    Integer disable = myRs.getInt("disable");

                    // create new user object
                    UserSearch tempUser = new UserSearch(un, password, email, address,
                            phoneNo, disable);

                    // add it to the list of users
                    users.add(tempUser);
                }

                return users;
            }
            finally {
                // clean up JDBC objects
                close(myConn, myStmt, myRs);
            }*/
        }

    }
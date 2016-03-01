package learn.spring.dao;


import learn.spring.core.dao.UserDAO;
import learn.spring.core.entity.User;
import learn.spring.utils.CalendarUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    private EmbeddedDatabase embeddedDB;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void prepare(){
        EmbeddedDatabaseBuilder embeddedDBBuilder = new EmbeddedDatabaseBuilder();
        embeddedDB = embeddedDBBuilder.setType(EmbeddedDatabaseType.H2)
                .addScript("test-users-data-h2.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDB);
    }

    @Test
    public void testPostConstruct() throws Exception {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1990);
        c.set(Calendar.MONTH, 10);
        c.set(Calendar.DAY_OF_MONTH, 20);
        User testUser = new User(1, "test_mail@mail.com", "FName", "LName", CalendarUtils.getCalendarWithoutTime(c.getTime()).getTime());
        UserDAO userDAO = new UserDAO();
        List<User> list = new ArrayList<>();
        list.add(testUser);

        Field usersListField = UserDAO.class.getDeclaredField("usersList");
        Field jdbcTemplateField = UserDAO.class.getDeclaredField("jdbcTemplateEmbedded");
        usersListField.setAccessible(true);
        jdbcTemplateField.setAccessible(true);
        usersListField.set(userDAO, list);
        jdbcTemplateField.set(userDAO, jdbcTemplate);

        Method postConsructMethod = UserDAO.class.getDeclaredMethod("registerUsersFromSpringConfig");
        postConsructMethod.setAccessible(true);
        postConsructMethod.invoke(userDAO);

        Object[] params = {testUser.getId()};
        User resultUser = jdbcTemplate.queryForObject("select * from dict_users where id = ?;", params, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthDay")
        ));

        assertNotNull(resultUser);
        assertEquals(testUser, resultUser);
        assertEquals(testUser.getId(), resultUser.getId());
        assertEquals(testUser.getEmail(), resultUser.getEmail());
        assertEquals(testUser.getFirstName(), resultUser.getFirstName());
        assertEquals(testUser.getFullName(), resultUser.getFullName());
        assertEquals(testUser.getLastName(), resultUser.getLastName());
        assertEquals(testUser.getBirthDay(), resultUser.getBirthDay());
    }

    @Test
    public void testGetUserById() throws Exception {
        UserDAO userDAO = new UserDAO();
        Field jdbcTemplateField = UserDAO.class.getDeclaredField("jdbcTemplateEmbedded");
        jdbcTemplateField.setAccessible(true);
        jdbcTemplateField.set(userDAO, jdbcTemplate);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1990);
        c.set(Calendar.MONTH, Calendar.FEBRUARY);
        c.set(Calendar.DAY_OF_MONTH, 15);
        Date expectedBirthDay = CalendarUtils.getCalendarWithoutTime(c.getTime()).getTime();

        User resultUser = userDAO.getUserById(5);

        assertNotNull(resultUser);
        assertEquals(Integer.valueOf(5), resultUser.getId());
        assertEquals("test_mail_1@mail.com", resultUser.getEmail());
        assertEquals("FirstName", resultUser.getFirstName());
        assertEquals("FirstName LastName-1", resultUser.getFullName());
        assertEquals("LastName-1", resultUser.getLastName());
        assertEquals(expectedBirthDay, resultUser.getBirthDay());
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        UserDAO userDAO = new UserDAO();
        Field jdbcTemplateField = UserDAO.class.getDeclaredField("jdbcTemplateEmbedded");
        jdbcTemplateField.setAccessible(true);
        jdbcTemplateField.set(userDAO, jdbcTemplate);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1995);
        c.set(Calendar.MONTH, Calendar.MAY);
        c.set(Calendar.DAY_OF_MONTH, 25);
        Date expectedBirthDay = CalendarUtils.getCalendarWithoutTime(c.getTime()).getTime();

        User resultUser = userDAO.getUserByEmail("test_mail_2@mail.com");

        assertNotNull(resultUser);
        assertEquals(Integer.valueOf(10), resultUser.getId());
        assertEquals("test_mail_2@mail.com", resultUser.getEmail());
        assertEquals("FirstName", resultUser.getFirstName());
        assertEquals("FirstName LastName-2", resultUser.getFullName());
        assertEquals("LastName-2", resultUser.getLastName());
        assertEquals(expectedBirthDay, resultUser.getBirthDay());
    }

    @After
    public void tearDown(){
        embeddedDB.shutdown();
    }
}

package learn.spring.utils;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.EventRating;
import learn.spring.core.entity.User;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by aftor on 07.01.17.
 */
public class JsonFilesParser {
    public List<User> parseUsers(MultipartFile file) throws IOException {
        ArrayList<User> usersList = new ArrayList<>();
        String content = getStringFromFile(file);
        JSONArray jsonArr = new JSONArray(content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        jsonArr.forEach(o -> {
            JSONObject jsonObject = (JSONObject)(o);
            Integer id = jsonObject.getInt("id");
            String firstName = jsonObject.getString("firstName");
            String lastName = jsonObject.getString("lastName");
            String email = jsonObject.getString("email");
            String birthDayString = jsonObject.getString("birthDay");
            Date birthDate = null;
            try {
                birthDate = dateFormat.parse(birthDayString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            User user = new User(id, email, firstName, lastName, birthDate);
            usersList.add(user);
        });
        return usersList;
    }

    public List<Event> parseEvents(MultipartFile file) throws IOException {
        ArrayList<Event> eventList = new ArrayList<>();
        String content = getStringFromFile(file);
        JSONArray jsonArr = new JSONArray(content);
        jsonArr.forEach(o -> {
            JSONObject jsonObject = (JSONObject)(o);
            Integer id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            Double basePrice = jsonObject.getDouble("basePrice");
            Integer minutesLength = jsonObject.getInt("minutesLength");
            int ratingId = jsonObject.getInt("ratingId");
            EventRating eventRating = EventRating.valueOf(ratingId);

            Event event = new Event(id, name, basePrice, eventRating, minutesLength);
            eventList.add(event);
        });
        return eventList;
    }

    private String getStringFromFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        return IOUtils.toString(inputStream, UTF_8.toString());
    }
}

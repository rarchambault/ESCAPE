package ca.mcgill.ecse428.ESCAPE.dao;

import ca.mcgill.ecse428.ESCAPE.model.UserProfile;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, String> {
    UserProfile findUserProfileByName(String name);
}
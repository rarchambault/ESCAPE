package ca.mcgill.ecse428.ESCAPE.dao;

import ca.mcgill.ecse428.ESCAPE.model.Admin;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, String> {
    Admin findAdminProfileByName(String aName);
}
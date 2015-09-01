package databases;

import java.util.List;

import models.User;
import models.iUser;

public interface iUserTable {
	public boolean create(User user);
	public List<User> get();
	public User getSingleUser(String name);
	public boolean update(User user);
	public boolean delete(iUser user);
}

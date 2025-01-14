package izh.springbootmvcapp.service;

import izh.springbootmvcapp.model.User;
import izh.springbootmvcapp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl {

    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) {
        userDao.save(user);
    }
    public void updateUser(Long id, User user) {
        User userUpdate = userDao.getReferenceById(id);
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setAge(user.getAge());
        userDao.save(userUpdate);
    }
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
    public User getUser(Long id) {
        return userDao.findById(id).orElse(null);
    }
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}

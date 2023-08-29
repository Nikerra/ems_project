package ems_project_2023;

import ems_project_2023.dao.repository.TaskRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ems_project_2023.dao.entity.Group;
import ems_project_2023.dao.entity.Role;
import ems_project_2023.dao.entity.TaskResponse;
import ems_project_2023.dao.entity.User;
import ems_project_2023.dao.repository.RoleRepository;
import ems_project_2023.dao.repository.TaskResponseRepository;
import ems_project_2023.dao.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Data
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;
    private final TaskResponseRepository taskResponseRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User= " + user + " not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    /***
     * Создание юзера при регистрации
     * @param user
     * @return
     */
    public boolean saveUser(User user) {

        User userFromDB = userRepository.findByName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setRole(new Role(2, "USER"));
        user.setGroupUser(new Group(1L, "Нет группы"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            List<TaskResponse> taskResponse = taskResponseRepository.findAllByUserId(userId);
            if (taskResponse != null) {
                taskResponseRepository.deleteAll(taskResponse);
            }
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<Role> allRoles() {
        return roleRepository.findAll();
    }


    public Role findRoleById(long id_role) {
        return roleRepository.getById(id_role);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByName(username);
    }

    public List<User> getUsersRoleTeacher() {
        Long id = 3L;
        return userRepository.getUserByRoleId(id);
    }
}
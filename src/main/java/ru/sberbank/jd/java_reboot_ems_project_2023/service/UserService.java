package ru.sberbank.jd.java_reboot_ems_project_2023.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Group;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.Role;
import ru.sberbank.jd.java_reboot_ems_project_2023.dao.entity.User;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.RoleRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.StudentRepository;
import ru.sberbank.jd.java_reboot_ems_project_2023.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Data
public class UserService implements UserDetailsService{

    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
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
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setRoles(new Role(2, "USER"));
        user.setGroup(new Group(0L,"Нет группы"));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
    public List<Role> findByIdIn(List<Long> roleIds) {
        return roleRepository.findAllById(roleIds);
    }

    public Role findRoleById(long id_role) {
        return  roleRepository.getById(id_role);
    }
    public void save(User user) {
        userRepository.save(user);
    }
}

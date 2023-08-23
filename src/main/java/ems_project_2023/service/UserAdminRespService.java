package ems_project_2023.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ems_project_2023.dao.entity.UserAdminResp;
import ems_project_2023.dao.repository.UserAdminRespRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminRespService {
    private final UserAdminRespRepository userAdminRespRepository;

    public List<UserAdminResp> getUserAdminRespByUser(Long  id) {
       return userAdminRespRepository.findAllByUserId(id);
    }

    public void save(UserAdminResp userAdminResp) {
        userAdminRespRepository.save(userAdminResp);
    }

    public List<UserAdminResp> allResp() {
        return userAdminRespRepository.findAll();
    }

    public UserAdminResp findByRespId(Long id) {
        return userAdminRespRepository.getById(id);
    }

}

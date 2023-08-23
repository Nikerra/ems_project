package ems_project_2023.dao.repository;

import ems_project_2023.dao.entity.UserAdminResp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAdminRespRepository  extends JpaRepository<UserAdminResp, Long> {

    List<UserAdminResp> findAllByUserId(Long userId);
}

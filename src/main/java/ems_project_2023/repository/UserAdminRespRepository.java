package ems_project_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ems_project_2023.dao.entity.UserAdminResp;

import java.util.List;

public interface UserAdminRespRepository  extends JpaRepository<UserAdminResp, Long> {

    List<UserAdminResp> findAllByUserId(Long userId);
}

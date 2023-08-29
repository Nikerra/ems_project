package ems_project_2023.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_resp")
public class UserAdminResp {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @JoinColumn(name = "text")
        private String text;

        @JoinColumn(name = "result")
        private Boolean result;

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
        private User user;

        @ManyToOne
        @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
        private Group group;

}


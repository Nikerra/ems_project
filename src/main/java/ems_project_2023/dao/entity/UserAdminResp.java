package ems_project_2023.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "userresp")
public class UserAdminResp {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String text;
        private Boolean result;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
        private User user;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
        private Group group;


}


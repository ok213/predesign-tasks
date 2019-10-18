package app.model;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleId;

    @Column(name = "role")
    private String authority;

//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "id")
//    private User user;

    public Authorities() {}

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}

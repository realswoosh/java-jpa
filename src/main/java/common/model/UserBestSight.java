package common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "user_best_sight")
public class UserBestSight {
    @Id
    @Column(name = "email")
    private String email;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    private String title;
    private String description;

    public UserBestSight() {
    }

    public UserBestSight(String email, User user, String title, String description) {
        this.email = email;
        this.user = user;
        this.title = title;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

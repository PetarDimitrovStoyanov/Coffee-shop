package bg.coffeshop.coffeeShop.model.entity;

import bg.coffeshop.coffeeShop.constant.RoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleEnum name;
    private List<UserEntity> userEntities;

    public Role() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public List<UserEntity> getUsers() {
        return userEntities;
    }

    public void setUsers(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}

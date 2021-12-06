package bg.coffeshop.coffeeShop.model.entity;

import bg.coffeshop.coffeeShop.constant.RoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleEnum name;
    private List<UserEntity> userEntity;

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

    @OneToMany(mappedBy = "role")
    public List<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(List<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }
}

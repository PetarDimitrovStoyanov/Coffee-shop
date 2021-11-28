package bg.coffeshop.coffeeShop.model.entity;

import bg.coffeshop.coffeeShop.constant.RoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleEnum name;
    private Set<UserEntity> userEntity;

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

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    public Set<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(Set<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }
}

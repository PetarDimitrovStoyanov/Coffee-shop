package bg.coffeshop.coffeeShop.model.binding;

import bg.coffeshop.coffeeShop.constant.GenderEnum;
import bg.coffeshop.coffeeShop.model.entity.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class UserRegisterBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private GenderEnum gender;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private Integer age;

    public UserRegisterBindingModel() {
    }

    @NotNull
    @Size(min = 2, message = "The length of the first name must be at least 2 symbols.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(min = 2, message = "The length of the last must be at least 2 symbols.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Size(min = 2, message = "The length of the username must be at least 2 symbols.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    @NotNull
    @Pattern(regexp = "[A-z].+[@][A-z]+[.][A-z+]+", message = "The email must contains @!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Size(min = 3, message = "The length of the password must be at least 3 symbols.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Min(value = 0)
    @Max(value = 150, message = "The age must be between 0 and 150.")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotBlank
    @Size(min = 3, message = "The length of the confirm password must be at least 3 symbols.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}

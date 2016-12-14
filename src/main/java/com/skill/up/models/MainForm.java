package com.skill.up.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by jpmc on 11/16/2016.
 */
@Entity
public class MainForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //validation sa firstname
    @NotEmpty(message = "Please enter your firstname.")
    @Pattern(regexp="^[A-Za-z]*$", message="Firstname must contain letters only.")
    private String firstname;
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    //validation sa lastname
    @NotEmpty(message = "Please enter your lastname.")
    @Pattern(regexp="^[A-Za-z]*$", message="Lastname must contain letters only.")
    private String lastname;
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    //validiation sa email
    @NotEmpty(message = "Email is required.")
    @Email(message = "Email is invalid")
    @Column(unique = true)
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Please provide a mobile number.")
    private Long mobileNumber;
    public Long getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private String comment;
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private boolean keepMeAnonymous;
    public boolean isKeepMeAnonymous() {
        return keepMeAnonymous;
    }

    public void setKeepMeAnonymous(boolean keepMeAnonymous) {
        this.keepMeAnonymous = keepMeAnonymous;
    }


}

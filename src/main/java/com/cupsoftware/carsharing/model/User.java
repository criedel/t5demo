package com.cupsoftware.carsharing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.apache.tapestry5.beaneditor.DataType;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.BeanEditor;
import org.apache.tapestry5.ioc.annotations.Inject;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.findByNameAndPasshash", query = "from User where name=:name and passhash=:passhash"),
    @NamedQuery(name = "User.findByName", query = "from User where name=:name")
})
public class User extends BaseEntity {

    private static final long serialVersionUID = -4740866844368343481L;

    @Validate("required,minlength=4,maxlength=30")
    @Column(length = 30, unique = true, nullable = false)
    private String name;

    @Transient
    @Validate("password")
    @DataType("password")
    private transient String password;

    @Transient
    @Validate("password")
    @DataType("password")
    private transient String passwordRepeat;

    @Column(length = 255, nullable = false)
    private String passhash;

    @Column
    private boolean rememberMe;

    /**
     * The annotated constructor will be used to initialize the bean when being
     * used with {@link BeanEditor} or {@link BeanEditForm}.
     */
    @Inject
    public User() {

    }

    public User(final String name,
                final String passhash) {

        super();
        this.name = name;
        this.passhash = passhash;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPasshash() {

        return passhash;
    }

    public void setPasshash(String passhash) {

        this.passhash = passhash;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPasswordRepeat() {

        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {

        this.passwordRepeat = passwordRepeat;
    }

    public boolean isRememberMe() {

        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {

        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {

        return name;
    }
}

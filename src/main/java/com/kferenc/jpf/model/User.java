package com.kferenc.jpf.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Users")
public class User implements org.springframework.security.core.userdetails.UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String timeZone;
    private String locale;
    private String avatar;
    private String signature;
    @Type(type = "com.kferenc.jpf.model.DateType")
    private Date birthDate;
    @Type(type = "com.kferenc.jpf.model.TimestampType")
    private Timestamp registeredDate;
    @Type(type = "com.kferenc.jpf.model.TimestampType")
    private Timestamp lastLoginDate;
    private Integer posts;
    private Integer messages;
    private Integer unreadMessages;
    @Type(type = "com.kferenc.jpf.model.DateType")
    private Date expirationDate;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserRoleRelations", joinColumns = {
        @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "roleId", referencedColumnName = "id", nullable = false)})
    private Collection<Role> roles;
    @OneToOne()
    @JoinColumn(name = "activityId")
    private Activity activity;

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getLocale() {
        return locale;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getSignature() {
        return signature;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public Integer getPosts() {
        return posts;
    }

    public Integer getMessages() {
        return messages;
    }

    public Integer getUnreadMessages() {
        return unreadMessages;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public Activity getActivity() {
        return activity;
    }

    public String getColor() {
        return roles.iterator().next().getColor();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public void setMessages(Integer messages) {
        this.messages = messages;
    }

    public void setUnreadMessages(Integer unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = new Role();
            role.setId(roleId);
            roles.add(role);
        }
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return !((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "com.kferenc.jpf.model.User[ id=" + getId() + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getSlug()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}

package web.backend.gothere.Repositories.Entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Table(name="Users")
@Entity(name="Users")
public class UserEntity implements UserDetails{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idUser;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private UserRole userRole;
	private Boolean locked;
	private Boolean enabled;
    private LocalDate signUpDate; 
    private String phoneNumber;
  
    public UserEntity (String email, String name, String lastName, String password, String phoneNumber ){
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = UserRole.USER;
        this.signUpDate= LocalDate.now();
        this.locked = false;
        this.enabled = false;
    }
    public UserEntity(){}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(simpleGrantedAuthority);
	}
    @Override
    public String getPassword() {
		return password;
	}
    @Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long id) {
        this.idUser = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public LocalDate getSignUpDate() {
        return signUpDate;
    }
    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }
    public Boolean getLocked() {
        return locked;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}

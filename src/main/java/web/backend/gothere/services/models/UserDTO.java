package web.backend.gothere.Services.Models;

public class UserDTO {
    private  Long idUser;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private String userRole;
	private Boolean locked;
	private Boolean enabled;

    
    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public Boolean getLocked() {
        return locked;
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
    public UserDTO(Long idUser, String email, String name, String lastName, String password, String userRole,
            Boolean locked, Boolean enabled) {
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.userRole = userRole;
        this.locked = locked;
        this.enabled = enabled;
    }
    public UserDTO() {
    }

    
}

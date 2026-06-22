import java.util.UUID;


public class User {

    private UUID id;
    private String email;
    private String password;

    

    public User(String email, int password) {
        
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        
    }

    

    // Getters
    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public int getPassword() {
        return password;
    }

    

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(int password ) {
        this.password = password ;
    }

    
}
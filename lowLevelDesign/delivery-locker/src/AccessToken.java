import java.util.Date;

public class AccessToken {
    private final String token;
    private final Compartment compartment;
    private Date createdAt;
    private Date expiresAt;

    public AccessToken(String token, Compartment compartment) {
        this.token = token;
        this.compartment = compartment;
        this.createdAt = new Date();
        this.expiresAt = new Date(createdAt.getTime() + 60 * 1000);
        System.out.println("Token created at: " + createdAt + ", expires at: " + expiresAt);
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public String getToken() {
        return token;
    }
}

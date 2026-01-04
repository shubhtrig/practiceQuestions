import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Locker {
    private final Map<String, AccessToken> compartments;
    private final List<Compartment> availableCompartments;
    private final Set<Compartment> occupiedCompartments;

    public Locker() {
        compartments = new HashMap<>();
        availableCompartments = new ArrayList<>();
        occupiedCompartments = new HashSet<>();

        availableCompartments.add(new Compartment(1, CompartmentSize.SMALL));
        availableCompartments.add(new Compartment(2, CompartmentSize.MEDIUM));
        availableCompartments.add(new Compartment(3, CompartmentSize.BIG));
        scheduleTokenCleanUp();
    }

    public String assignCompartment(CompartmentSize size) {
        Compartment tempComp = availableCompartments.stream()
                .filter(comp -> comp.getSize() == size)
                .filter(comp -> !occupiedCompartments.contains(comp))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No available compartment of the requested size " + size.toString())
                );

        String token = getRandomToken();
        System.out.println("Generated token: " + token);
        AccessToken accessToken = new AccessToken(token, tempComp);
        compartments.put(token, accessToken);
        occupiedCompartments.add(tempComp);
        return token;
    }

    public Compartment retrieveCompartment(String token) {
        if (!compartments.containsKey(token)) {
            throw new RuntimeException("Invalid token: " + token);
        }

        AccessToken accessToken = compartments.get(token);
        if (accessToken.getExpiresAt().before(new Date())) {
            freeCompartment(accessToken);
            throw new RuntimeException("Token has expired: " + token);
        }

        freeCompartment(accessToken);
        return accessToken.getCompartment();
    }

    private String getRandomToken() {
        String token = String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999));
        while (compartments.containsKey(token)) {
            token =  String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999));
        }
        return token;
    }

    private void scheduleTokenCleanUp() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::freeExpiredAccessTokens, 0, 1, TimeUnit.MINUTES);
    }

    private void freeCompartment(AccessToken token) {
        Compartment comp = token.getCompartment();
        System.out.println("Freeing compartment: " + comp.getSize() + " with id: " + comp.getId());
        occupiedCompartments.remove(comp);
        compartments.remove(token.getToken());
    }

    private void freeCompartments(List<AccessToken> tokens) {
        tokens.forEach(this::freeCompartment);
    }

    private void freeExpiredAccessTokens() {
        List<AccessToken> expiredTokens =
                compartments.values()
                        .stream()
                        .filter(token -> token.getExpiresAt().before(new Date()))
                        .toList();
        freeCompartments(expiredTokens);
    }
}

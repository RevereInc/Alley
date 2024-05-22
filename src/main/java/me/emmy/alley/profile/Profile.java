package me.emmy.alley.profile;

import lombok.Getter;
import lombok.Setter;
import me.emmy.alley.database.profile.IProfile;
import me.emmy.alley.match.AbstractMatch;
import me.emmy.alley.profile.enums.EnumProfileState;
import me.emmy.alley.queue.QueueProfile;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    public static IProfile iProfile;

    public static final int DEFAULT_COINS = 100;
    public static final int DEFAULT_ELO = 1000;
    private QueueProfile queueProfile;
    private EnumProfileState state;
    private AbstractMatch match;
    private final UUID uuid;
    private boolean online;

    /**
     * Constructor for the Profile class.
     *
     * @param uuid The UUID of the player.
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.state = EnumProfileState.LOBBY;
    }

    /**
     * Loads the profile from the database.
     */
    public void load() {
        iProfile.loadProfile();
    }

    /**
     * Saves the profile to the database.
     */
    public void save() {
        iProfile.saveProfile();
    }
}
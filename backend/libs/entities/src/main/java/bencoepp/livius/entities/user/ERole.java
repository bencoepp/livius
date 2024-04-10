package bencoepp.livius.entities.user;

/**
 * Represents the possible roles in a system.
 * <p>
 * The ERole enum defines the different levels of access or permissions
 * available to users within a system. It is used to model the role
 * types within a system.
 * <p>
 * This enum provides the following values:
 * - ROLE_USER: Represents a user role.
 * - ROLE_MODERATOR: Represents a moderator role.
 * - ROLE_ADMIN: Represents an admin role.
 * - ROLE_PRIVILEGED: Represents a privileged user
 * <p>
 * These roles can be used to determine the level of access or permissions a
 * user has within the system.
 */
public enum ERole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN,
    ROLE_PRIVILEGED
}

# Roles

To make it easier to distinguish who can do what in the livius service there are currently four existing roles in livius.

- [ROLE_USER](#user)
- [ROLE_PRIVILEGED](#privileged)
- [ROLE_MODERATOR](#moderator)
- [ROLE_ADMIN](#admin)

These roles give the user a set of privileges and responsibilities that are explained below. 

## User

This is the role that 95% of livius users will have. This role allows access to the api, enables the user to comment and 
discuss data in livius as well as interact with the ui in more ways than a user which is not logged in.

Only restrictions that apply to this role is that a user as a rate limit on the api so as to not slow down the entire 
service for everyone. And he is not allowed to contribute data, delete data or modify existing data. 

## Privileged

If you need a bit more speed, and or you need the limits of the api removed then you can become a privileged user. A
privileged user is a user that has no api access limits and is allowed full control over the data issued to him. 
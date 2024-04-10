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

## Moderator

A Moderator is a user which not only has all the privileges that the normal user and a privileged user has as well as the
rights to moderate the data and other content in livius. This type of user is normally a contributor of a dataset.

To become a moderator you need to be a privileged user and then contact us through either the ui or the api with a reason
why you should become a moderator.

## Admin

Admins are the people that manage livius instances. For each instance there needs to be at least one moderator. For the 
public livius instance the current admin is the development lead Ben Cöppicus. If you run a livius instance for you team,
your school, university or research endeavor. We would be interested in linking the livius instances to share data and 
information, this would also mean that for the parts that are shared you would be promoted to admin.
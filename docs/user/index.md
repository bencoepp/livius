# General

In this chapter of the documentation you can find out about the user service, its importance in the livius backend. As
well as how to interact with it. Please note that it is not required to have a user account to access livius.io, but if 
you wish to interact with the api then you do need one. Instructions on how to create a user account can be found 
[here](/getting-started.html).

As Livius is a open source project and we do not charge for the data that we provide, as that data does not belong to us
and most if not all datasets have there own licenses. We do need a way to stop abuse of our apis and the misuse of the 
data. For that very reason everyone can access the ui and use livius.io but if you want to access the apis behind it 
you either need to host your own version of livius on your own infrastructure or register for an account. Accounts are 
always free and you will not be charged for any usage. But there are some limitations on what you can do with the public 
api. 

## Limitations

As a normal user of the api you can access all data freely, but editing, deleting or adding data to the service is not 
possible. Furthermore there is a rate limit on api calls so as to not slow down our server to much. If you want to lift
these rate limits contact us at ben.coeppicus@bencoepp.de so we can discuss how to handle this. This will either lead to
us helping you setup your own livius instance or you promising us to contribute to the project in some way.

## Authentication & Authorization 

Below we discuss how to use the [user endpoints](/user/endpoints.html) to setup an account and then issue your first
api request. The first thing to do is send the following JSON data to [api.livius.io/api/v1/auth/signup](https://api.livius.io/api/v1/auth/signup).

```json
{
    "username" : "test",
    "email" : "test@livius.io",
    "roles" : ["user"],
    "password" : "LiviusTest123!"
}
```

Once you have done this, you should get a message returned that states that the user was registered successfully. Please
also note that only the user role is accessible in the registration process. If you need higher privileges you can contact 
us or request an alleviation through the api or ui.

Once you have successfully registered yourself with the livius service you need to login to get a jwt token. This can be
done by sending the following JSON data to [api.livius.io/api/v1/auth/signin](https://api.livius.io/api/v1/auth/signin).

```json
{
    "username" : "test",
    "password" : "LiviusTest123!"
}
```

If you have provided the correct credentials you will get an output similar to the one below.

```json 
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzEyNzM5MzQwLCJleHAiOjE3MTI4MjU3NDB9.ysI-VgKlKDuoinnU7mptxmPP7HZYtUyq1rOqL5WxCE8",
    "type": "Bearer",
    "id": "661653758003164bcf50e5b5",
    "username": "test",
    "email": "test@livius.io",
    "roles": [
        "ROLE_USER",
    ]
}
```

You now just need to pass the token with your headers in any request you send to the api. For further information please 
refer to the [user endpoints](/user/endpoints.html).
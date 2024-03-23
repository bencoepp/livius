# Service

The weather service provides data for two separate areas of the application. The first area is simply the displaying, 
analyzing and visualizing weather data on its own. You can find this in the application under the weather tab. 

The second area is by association with the annotation service. This service will try to fit other events, as well as 
data to a specific weather event. This means for instance, that a specific battle or event in history will get a time 
and location based annotation to the respected weather data. This might not necessarily be true, as the weather data injected 
into livius might not be correct. But generally this can be used as a rough guide. 

## Structure of the Service

The structure of the service can be seen below. Please note that the illustration mainly shows how data is injected as well
as how data can be retrieved from the service. 

## Usage & Limitations

For all developers that want to use our api for their own apps as well as services please keep the following things in mind.
All of our weather data is currently retrieved from the following source [NOAA Global Hourly Surface Data](https://www.dolthub.com/blog/2020-03-02-noaa-global-hourly-surface-data/).
That means that you NEED to comply with there license as well as usage guidelines if you want to use any data outside of 
the livius project. 

When it comes to simple accessing the UI you do not need a user account. If you plan to use the api you do need one as to 
obtain a api key. Without an issued api key all requests to the service will be blocked. Furthermore we recommend that you
only use the public api for the weather data only if you have too. And if you intend to use it for a long time, or want to
issue a lot of requests to it we would love for you to support the project financially so that the service costs can be covered.

If you do not want to contribute financially to the project, but still need a lot of requests to the weather service we 
recommend you use the replication service to bootstrap your own livius instance. A guide to set this up can be found 
[here](/getting-started).
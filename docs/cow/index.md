# Correlates of War

The main data that we use for visualizing and the internal workings of livius is the correlates of war project. This
data is used in nearly all our tools, features and functions. Meaning that if you plan on using any of livius features in
your own work you MUST make sure that you adhere to the respected license of the dataset in question. 

## How do we gather the data

All data that livius consumes of the correlates of war project are imported using the data service. A detailed description 
of what that service does can be found [here](/services/#data). Data is imported from the public archive of the correlates 
of war project website. [https://correlatesofwar.org/](https://correlatesofwar.org/). For each dataset we grab the respected
resource, download it using the data service and then load it into the database. To make the ease of use as well as the 
tools we provide more homogeneous we transform all .csv files into data that can be read and understood by Spring JPA.

The data is left untouched in the sense that all values will be imported and can be accessed in livius. But the keys/names
by which they are accessed were changed to better reflect modern naming and coding standards.

## Where is the data used

The main point where the data is used is in the endpoints of the cow service. Here a user can interact with the data 
in a variety of ways as well as gather prebuild comparisons. This will probably be where most people interact with the 
data. The next place the data is directly used is in the annotation service. The annotation service goes through nearly 
all datasets and links them with articles, studies, images and other resources that explain exactly what the data is
referring to. The data itself will not be changed.

The last place the data is used in is in references to other datasets used, like weather data and so on.

## Limitations & Restrictions

To make sure that no misuse of data is taking place as well as to make sure that everyone using the data is aware of the
limitations and restrictions applied to them. We add a Citation object to the response. This object includes information
on how to use the data, what you can do with the data and what you can not do with that data. Please note that this
information is directly gathered from [https://correlatesofwar.org/](https://correlatesofwar.org/). If you have any
questions please refer to the original authors.

## Acknowledgment

The big thing that needs to be acknowledged is the great work the people of and around the correlates of war project have
done and are still doing. It is a tremendous undertaking to compile, order and research such a variety of datasets and keep 
them in good order. Not to mention the great license and usage. 

This project does not in any why take credit for the work these people listed below have done and are continuing to do. 
And as already mentioned a bunch of times throughout the documentation please go ahead and read, and support the official 
datasets so that the work on them can continue. 

### Current Directors

- [Jeff Carter](http://jeffcarter.weebly.com/) (Appalachian State University)
- [Scott Wolford](http://www.scott-wolford.com/) (University of Texas)

### Past Directors

- [Zeev Maoz](http://maoz.ucdavis.edu/) 2013-2022
- [Paul F. Diehl](maileto:pdiehl@illinois.edu) 2005-2012
- [D. Scott Bennett](http://www.personal.psu.edu/dsb10/) 2005-2012
- [Stuart Bremer](http://journals.cambridge.org/download.php?file=%2FPSC%2FPSC36_02%2FS1049096503002294a.pdf&code=bdf66c3b646952e908c095f29250a990) (interim), 2002-2004
- [J. David Singer](https://archive.ph/20121211095931/http://sitemaker.umich.edu/jdsinger/home) (deceased), 1963-1998

### Current Associate Director

- [D. Scott Bennett](http://polisci.la.psu.edu/people/dsb10) Pennsylvania State University (since 2005)

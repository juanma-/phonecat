# Phone Catalog Tutorial Application

## Overview

This application takes the developer through the process of building a web-application using
angular. The application is loosely based on the **Google Phone Gallery**, which no longer
exists. Here is a historical reference: [Google Phone Gallery on WayBack](http://web.archive.org/web/20131215082038/http://www.android.com/devices/).

## Prerequisites

### Git

- A good place to learn about setting up git is [here][git-github].
- Git [home][git-home] (download, documentation).

## Application Directory Layout

    backend/            --> all of the files to be used in production
      contracts/        --> css files
        delivery/       --> default stylesheet
        external/       --> default stylesheet
      controllers/      --> image files
      model/            --> app layout file (the main html template file of the app)
      repositories/     --> javascript files
      use-case/
      webapp/
    db/                 --> angular view partials (partial html templates) used by ngRoute
      migration/
        V1__xxx.sql     --> scripts 
      phonecat.h2.db    --> h2 database
    frontend/

## Contact

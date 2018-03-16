# Facebook-SDK-query-Mobile-App

Implement Facebook node query with AngularJS and Bootstrap for mobile website and Android app, PHP for back-end on Amazon AWS

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites



Android part:
You need to install Android studio and import this project in it.
You can download Android studio here:
https://developer.android.com/studio/index.html


PHP part:
You need to get one Facebook Application and short access token:

```
Give examples
```

### Installing
Android part:

You need to change the link direct to index.php:
```
Give an example
```

PHP part:
you need to change the code in index.php

```
require_once __DIR__ . '/php-graph-sdk-5.0.0/src/Facebook/autoload.php';
	$access_token = "your_access_token";
	$fb = new Facebook\Facebook([
        'app_id' => 'your_app_id',
        'app_secret' => 'your_app_secret',
        'default_graph_version' => 'v2.9',
      ]);
```

and put all th folder in the apache server

## Running the tests

You can dirtect run it on Android phone or on your Computer

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

<!-- 
## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.
 -->
## Authors

* **Wei-Nung Chao** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.


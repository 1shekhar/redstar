# Embolder - Embold Test Automation
### _Automated validation for UI, API and DB_
------

## Features

- Pre-configured Maven based Java project. This project is **currently** powered by Selenium WebDriver and TestNG.
- Can be run via Terminal or IDE (Eclipse / IntelliJ) as standalone maven project
- Continuous Integration using Jenkins
- Effective Test Report generation using Allure

> Please note, this project is currently configured for Google Chrome, Mozilla Firefox and Microsoft Edge. Rest browsers are not supported yet.

## Prerequisites

Following configuration is needed in order to run this project locally.

- [Apache Maven] 3.5+ (https://maven.apache.org/)
- [JRE] 8u171+(https://8u171.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- [Allure](https://docs.qameta.io/allure/#_installing_a_commandline)

> After installing above, make sure you have three environment variables set in your local machine - `${JAVA_HOME}`, `${M2_HOME}`, `${ALLURE_HOME}`

## Test Execution
Test execution is much simpler. You can do this by executing only one command or by running preconfigured Jenkins job.

Clone this repository

```sh
mkdir uitests && cd uitests
git clone https://github.com/1shekhar/redstar
```

Run maven goal. You can configure above goal by providing some custom `${env}` variables. Currently you can provide browser and platform on which tests needs to be run. 
You can give your localhost URL too. For example,
```sh
mvn clean install -Dbrowser=firefox
```
or
```sh
mvn clean install -Dbrowser=edge -DappURL=https://v2.emboldct.dev
```
### Supported Environment Variables

| Environment Variable | Supported Value | Default Value |
| ------ | ------ | ------ |
| browser |chrome, edge, firefox | chrome |
| appURL | Any valid URL | https://app.gamma-staging.com |
| ghUser | Provide GitHub Username | NA |
| ghPass | Provide GitHub Password | NA |
| bbUser | Provide Bitbucket Username | NA |
| bbPass | Provide Bitbucket Password | NA |

When you run mvn command without providing any custom `${env}` by simply executing 
```sh
mvn clean install
```
Execution will be carried out with **Google Chrome** in non-headless mode on **[V2 Staging](https://app.gamma-staging.com)** 
In this case, tests will use **Test_Credentials** of GitHub and Bitbucket account logins.
> Please note, execution will fail if Google Chrome or the browser you selected for execution is not installed on your local machine.

## Test Report Generation

Test report generation is carried out by using Allure framework. 
After successful / unsuccessful execution, **allure-results** directory gets created at root location (where pom.xml is located).
Simply run below command at root location
```sh
allure serve allure-results
```
_That's all!!! All you need to do is, run__

**`mvn clean install && allure serve allure-results`**

Whenever new changes are deployed, you can run above command with your required `${env}` values and see the generated report right after execution.


>Note: Allure report is not static report and hence cannot be downloaded. Refer [this issue](https://github.com/allure-framework/allure2/issues/755)

--------------------------------------

## Configuration with Jenkins
--------------------------------------

In order to run the tests remotely on Jenkins, you can refer below job.
> http://192.168.2.24:8080/job/emboldv2/job/embolder/

Build this Job with required parameters. Parameters are nothing but Environment Variables we do provide to this project. In case no values provided, tests will run with default configuration as mentioned earlier.

> Allure Report is generated on same Jenkins instance. You can view it same workspace.

> You cannot execute this job for your local changes. For local, you need to configure project locally and follow previous instructions or give your machine IP as `${appURL}`to Jenkins build.

> This job is configured to run on `master` node where all supported browsers are installed

## Development

Want to contribute? Great!

Clone this repository and ask for collabortion access to owner.
#### Building for source

_Currently there are some issues with some dependencies. Will share build steps later._

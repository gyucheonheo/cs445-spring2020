# Share A Ride
![Java CI](https://github.com/gyucheonheo/cs445-spring2020/workflows/Java%20CI/badge.svg)

## Configuration Instructions

Assume that your working environment is Ubuntu 18.04 LTS.

* Update
```
$>sudo apt update
```
* Install openjdk-8-jdk
```
$>sudo apt install openjdk-8-jdk
```
* Check Java version
```
$>java -version
```
* If Java version is not 1.8, update the version to 8 by selecting the appropriate number.
```
$>sudo update-alternatives --config java
```
* Install Maven
```
$>sudo apt install maven
```
## Build and Deploy Instructions
* Install and Compile (Assume that you are under a directory where pom.xml exists)
```
$>mvn install compile test
```
* Run the webserver
```
$>mvn exec:java
```
## Copyright and License

Permission is hereby granted, free of change, to any person obtaining
a copy of this software and associated documentation files (the "Software"),
to deal in Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute and/or sublicense copies of the Software,
and to permit persons to whom the Software is furnished to do so, subject to the following conditions.

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTIO WITH THE SOFTWARE
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Known Bugs



## Credits and Acknowledgements

Author of the Share a Ride Documentation
* Gyucheon Heo

Author of the Share a Ride version 1.0
* Gyucheon Heo

Contact Information
* Gyucheon Heo
* email : gheo1[at]hawk[dot]iit[dot]edu
* mobile: +1 224 209 3797

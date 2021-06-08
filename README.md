This repository contains everything needed to run the 'cd' simulator.

After cloning the repo by running `git clone https://github.com/gyaanantia/mycd.git`:

To use the Python file, cd into the repo folder and use `python3 mycd.py <arg1> <arg2>`.
The Python file has built in tests, so if it runs, all the tests have passed.
  
To use the Java files, cd into the repo folder and use `java Mycd <arg1> <arg2>`.
To run the tests, use `java -cp junit-4.13.2.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MycdTester`.
NOTE: if you are using Windows command prompt, change the `:`s to `;`s
The output should read "OK (12 tests)".
This means the tests have passed.


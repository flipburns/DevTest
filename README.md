# Philip Burns North Plains Interview Tech Task

how to build:

import project into IDE using build.gradle as driver for project
run command `gradle build` from root dir
run the Main.main() method. This will perform all of the requested actions and also print a line specifying the location of the new customers file with the updated addresses.

e.g. 'New File written to: C:\Users\Philip\Downloads\nps_dev_test.tar\DevTest\build\resources\main\enhancedData.json' - this indicates where the new file will appear


notes:

- definitely got lazy on the tests towards the end, as it's a Sunday evening
- parallelism isn't immediately obvious, but I used parallel streams, which noticably reduced the execution time by a few seconds
- used pythagoras to calculate rough distance between lat and long points
- although the assignment specifies production code - there are many things which I would change to productionise it but didnt due to time constraints. This includes more tests, not using any system printlns, and probably more comments
- i opted for a very simple address implementation - in reality this would be much more abstract and reliable
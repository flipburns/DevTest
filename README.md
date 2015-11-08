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


output of code on local machine (in the event it doesnt work elsewhere):

```
Most Common EyeColour:
BROWN


All customer emails in ascending order:

aimee.fisher@utarian.me
amanda.conrad@extragen.info
beryl.young@plasmox.co.uk
bradshaw.coffey@evidends.tv
carly.woodward@daycore.ca
carter.rutledge@verbus.net
chandler.brown@xleen.org
deborah.adams@extrawear.org
glover.whitney@mangelica.me
graham.duran@supremia.us
herring.miller@bovis.net
holcomb.burks@bostonic.com
huffman.ryan@iplax.com
irene.langley@netbook.co.uk
jocelyn.bond@extremo.net
long.frye@limozen.io
mcclure.rush@zaphire.io
michele.hampton@geostele.us
monica.lott@corepan.biz
nannie.walter@enquility.us
odessa.case@ginkogene.info
patricia.bender@techtrix.com
penny.wolfe@zillan.io
quinn.gay@insurety.co.uk
ryan.miranda@mantro.biz
simon.curtis@magmina.biz
tillman.fitzgerald@ziore.ca
vega.nunez@exoplode.tv
whitley.webb@infotrips.biz
wise.acevedo@pivitol.biz

Closest Customers:

Closest customers are com.example.model.Name@2eda0940[first=Wise,last=Acevedo,additionalProperties={}] and com.example.model.Name@3578436e[first=Tillman,last=Fitzgerald,additionalProperties={}] who have 0.015009931445550994 between them

New File with updated info written to: C:\Users\Philip\Downloads\nps_dev_test.tar\DevTest\build\resources\main\enhancedData.json
Time Taken: 1.730906623 seconds

Process finished with exit code 0
```

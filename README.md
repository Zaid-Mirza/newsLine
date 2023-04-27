
News Line (Showing Articles)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)
![Language](https://img.shields.io/badge/language-Kotlin-orange.svg)


<div align="center">
  <sub>Developed by
  <a href="https://twitter.com/zaidmirzait">Zaid Mirza</a> and
  <a href="https://github.com/Zaid-Mirza/AttachmentManager/graphs/contributors">
    contributors
  </a>
</div>
<br/>

The news line a simple app that shows most popular news articles using Newyork API. It provides basic data filter options.




## Installation

The apk file is uploaded to diawi.com. Install directly using the link 

```
  https://i.diawi.com/RYFUb8
```

You may see warning messages related to unknown or harmful app. They message will warn you because we are not downloading the APK from Google Play Store. Just Hit allow, Download Anyway or whatever posstive message appear to install the app.

After app is installed. NewsLink App will appear within device Apps List.


[![news-report.png](https://i.postimg.cc/NFp2gK5z/news-report.png)](https://postimg.cc/Lgg8D6kt)
## How to Run the Code

Basic Requirement:

* JDK
* Latest Android Studio IDE
* Emulator or Physical Device (Android OS >= 26)

Clone the repository using Android studio IDE (recommended)



* Master Branch
```
https://github.com/Zaid-Mirza/newsLine.git
```


## Unit Tests and Coverage Reports

To run the unit tests independently,

Open Test folder as shown below, there are two files create to test the basic functionality for sake of this assessment.

Lets start with ```ArticleTest```. This contains a basic test to check whether the ```fetchArticles``` functions inside the ```ArticleRepository``` is working.

[![Screen-Shot-2023-04-28-at-12-44-54-AM.png](https://i.postimg.cc/HxMfDMPT/Screen-Shot-2023-04-28-at-12-44-54-AM.png)](https://postimg.cc/bDz61Zh5)
#### Run the Test ```verifyArticlesResult```

* Click  the Green triangle button on left hand side of the function body

[![Screen-Shot-2023-04-27-at-11-33-57-PM.png](https://i.postimg.cc/43kyR6W3/Screen-Shot-2023-04-27-at-11-33-57-PM.png)](https://postimg.cc/G4Kdkyw0)

* Run with Coverage (If you want to generate reports)
[![Screen-Shot-2023-04-27-at-11-34-13-PM.png](https://i.postimg.cc/fbNzrwbt/Screen-Shot-2023-04-27-at-11-34-13-PM.png)](https://postimg.cc/gxgbwb8m)

* Here you can change the values of parameters passed to ```verifyArticlesResults``` method to test different scenarios.
[![Screen-Shot-2023-04-27-at-11-36-34-PM.png](https://i.postimg.cc/zv65SNMf/Screen-Shot-2023-04-27-at-11-36-34-PM.png)](https://postimg.cc/dZ2zwbJM)

* When tests finished running. You may see following view within Android Studio
[![Screen-Shot-2023-04-28-at-12-53-36-AM.png](https://i.postimg.cc/7LJzg1L1/Screen-Shot-2023-04-28-at-12-53-36-AM.png)](https://postimg.cc/4KZnG9Vn)

* Above image shows that tests are finished and coverage reports generated by Android Studio. If you want to open coverage reports as Html page. You can find all reports generate at path below:


Open Projecr Root -> app -> build -> reports -> tests -> testDebugUnitsTest


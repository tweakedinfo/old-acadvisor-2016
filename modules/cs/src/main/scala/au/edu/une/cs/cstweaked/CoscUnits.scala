package au.edu.une.cs.cstweaked

import info.tweaked.model.content._
import info.tweaked.model.plan.Prerequisite.minCP
import info.tweaked.model.unit._

/**
 * Holds definitions of COSC units
 */
object CoscUnits {

  val AMTH140 = TeachingUnit(
    "AMTH140",
    name = Some("Discrete Mathematics")
  )

  val AMTH150 = TeachingUnit(
    "AMTH150"
  )

  val MTHS120 = TeachingUnit(
    code = "MTHS120",
    name = Some("Calculus and Linear Algebra 1")
  )

  val MTHS130 = TeachingUnit(
    code = "MTHS130",
    name = Some("Calculus and Linear Algebra 2"),
    prerequisite = MTHS120
  )

  val MTHS110 = TeachingUnit(
    code = "MTHS110",
    name = Some("Quantitative Skills with Applications")
  )

  val MTHS100 = TeachingUnit(
    code = "MTHS100",
    name = Some("Introduction to Quantitative Skills")
  )

  val STAT100 = TeachingUnit(
    code = "STAT100",
    name = Some("Introduction to Statistical Modelling")
  )

  val STAT210 = TeachingUnit(
    code = "STAT210",
    name = Some("Statistical Modelling and Experimental Design"),
    prerequisite = STAT100 or AMTH150
  )

  val STAT330 = TeachingUnit(
    code = "STAT330",
    name = Some("Statistical Learning"),
    prerequisite = STAT210
  )

  val COSC101 = TeachingUnit(
    "COSC101",
    name=Some("Software Design Studio 1")
  )


  val COSC110 = TeachingUnit(
    "COSC110",
    name=Some("Intro to Programming and the UNIX environment")
  )

  val COSC120 = TeachingUnit(
    code = "COSC120",
    name = Some("Object oriented programming"),
    prerequisite = COSC110 or AMTH150
  )
  val COSC210 = TeachingUnit(
    code = "COSC210",
    name = Some("Database Management Systems"),
    prerequisite = COSC110 or AMTH150
  )
  val COSC220 = TeachingUnit(
    code = "COSC220",
    name = Some("Software Engineering Studio"),
    prerequisite = COSC120
  )
  val COSC230 = TeachingUnit(
    code = "COSC230",
    name = Some("Data Structures and Algorithms"),
    prerequisite = AMTH140 and COSC120
  )
  val COSC240 = TeachingUnit(
    code = "COSC240",
    name = Some("Operating Systems"),
    prerequisite = COSC120
  )
  val COSC250 = TeachingUnit(
    code = "COSC250",
    name = Some("Programming Paradigms"),
    prerequisite = COSC120
  )
  val COSC260 = TeachingUnit(
    code = "COSC260",
    name = Some("Web Programming"),
    prerequisite = COSC110 and COSC120
  )
  val COSC310 = TeachingUnit(
    code = "COSC310",
    name = Some("Software Project Management"),
    prerequisite = COSC220 and minCP(72) //96
  )
  val COSC320 = TeachingUnit(
    code = "COSC320",
    name = Some("Information Technology Project"),
    prerequisite = COSC220 and COSC310 and minCP(96) // 24
  )
  val COSC330 = TeachingUnit(
    code = "COSC330",
    name = Some("Parallel and Distributed Computing"),
    prerequisite = COSC230 or COSC240
  )
  val COSC340 = TeachingUnit(
    code = "COSC340",
    name = Some("Computer Networks and Information Security"),
    prerequisite = AMTH140 and COSC240 and minCP(72)
  )
  val COSC350 = TeachingUnit(
    code = "COSC350",
    name = Some("Artificial Intelligence"),
    prerequisite = COSC230
  )



  val COSC360 = TeachingUnit(
    code = "COSC360",
    name = Some("Advanced Web Programming"),
    prerequisite = COSC260 and COSC210 and COSC120
  )

  val COSC380 = TeachingUnit(
    code = "COSC380",
    name = Some("Advanced Computational Science"),
    prerequisite = (AMTH150 or COSC110) and MTHS120 and minCP(48)
  )

  val COSC370 = new TeachingUnit(
    code = "COSC370",
    name = Some("User Experience and Interaction Design"),
    outcomes = Seq(
      "apply design methods and thinking techniques to generate unique designs for interactive programs;",
      "apply theories of human computer interaction to analyse and inform program designs;",
      "write interactive programs that use a variety of input techniques, including for mobile devices;",
      "empirically investigate how users interact with programs; and",
      "apply common programming design patterns, structures, and development practices for interactive programs."
    ),

    prerequisite = COSC220 and minCP(72),

    readyQuiz = Some(ReadyQuiz(Seq(
      RQMCQuestion(
        "Are you reasonably comfortable with the idea of talking to people during the unit, and recording video explaining what you're doing (it doesn't have to be very slick video)?",
        Seq(
          RQMCOption("Yes",
            "That's good. As well as investigating your users, and trying your app out, you're also going to be part of a design community. So at various points in the unit we're going to ask you to record little videos of your design, and to watch the videos of other students and comment on their work too."
          ),
          RQMCOption("No",
            """
              | Ok, just be prepared for the fact we're going to ask you to do that at a few points in the unit!
              | As well as investigating your users, and trying your app out, you're also going to be part of a design
              | community. So at various points in the unit we're going to ask you to record little videos of your
              | design, and to watch the videos of other students and comment on their work too.
            """.stripMargin
          )
        )
      ),
      RQMCQuestion(
        """
          |In this unit, you're going to need to build an interesting app in a short space of time. So it is going to be important that you already have some programming skills. For example, our UNE students have typically taken three programming units before they reach this unit.
          |
          |Have you written programs in Java or Swift before (of more than ten classes)?
        """.stripMargin,
        Seq(
          RQMCOption(
            "Yes, I've written programs in Java",
            """
              | That's good. Android apps are primarily written in Java. In the unit you will need to write an app in a
              | fairly short space of time, so being reasonably confident at writing code in Java is helpful.
            """.stripMargin
          ),
          RQMCOption(
            "Yes, I've written programs in Swift",
            """
              | Ok, but you will find the unit a little harder. We primarily teach the unit using Android,
              | but you are permitted to do your project using Swift for Apple devices if you wish.
              | But please note that we won't have teaching materials available for the Swift/iPhone side of things.
              | (But there are quite a few resources for that out there on the web.)
            """.stripMargin
          ),
          RQMCOption(
            "I've written larger programs, but not in Java or Swift",
            """
              | Ok, but you will find the unit a little harder. We teach the unit using Java for Android.
              | But there are frameworks that will let you write a mobile app in many languages -- however, you would
              | need to investigate those yourself (we don't have materials for every language).
            """.stripMargin
          ),
          RQMCOption(
            "I've only written smaller programs, but I feel I can grow my skills quickly",
            """
              | Ok, but I'd really recommend getting some programming practice in early, before the unit starts.
              | For example, we put an example Android project up on GitHub [link to project].
              | Download it, and see if you can get a head start on understanding what's going on.
            """.stripMargin
          ),
          RQMCOption(
            "I'm not sure what a \"class\" is",
            """
              |  It sounds as though you might find this unit hard. I'd recommend improving your programming skills
              |  before attempting this unit, as you might struggle to produce a working prototype in a short space of
              |  time.
            """.stripMargin
          )
        )
      ),
      RQMCQuestion(
        "Have you written programs that use event handlers before?",
        Seq(
          RQMCOption(
            "Yes",
            "That's good."
          ),
          RQMCOption(
            "No",
            """
              | I'd recommend writing a few programs using events before the unit starts. Quite a lot of app code is
              | about handling events -- be it button presses, the app being put into the background, or receiving data.
              | It's not very complicated, but can be a little different than thinking about a program just as a
              | sequence of instructions.
            """.stripMargin
          ),
          RQMCOption(
            "I'm not sure what an event handler is",
            """
              | I'd strongly recommend reading up on this and writing a few programs using events before the unit
              | starts. Quite a lot of app code is about handling events -- be it button presses, the app being put into
              | the background, or receiving data.  It's not very complicated, but can be a little different than
              | thinking about a program just as a sequence of instructions.
            """.stripMargin
          )
        )
      ),
      RQMCQuestion(
        "Do you have a phone or tablet that you can enable the \"developer mode\" on (so you can run your program on it)",
        Seq(
          RQMCOption(
            "Yes, I have a recent Android phone",
            "That's good. It's always much easier to develop apps when you can try them out!"
          ),
          RQMCOption(
            "Yes, I have an older Android phone or tablet",
            "Ok, that's fine. Older phones or tablets might not have the full range of sensors to play with, and there can sometimes be version compatibility issues between Android devices. "
          ),
          RQMCOption(
            "Yes, I have an iPhone or iPad",
            """
              | Ok, but you will find the unit a little harder. We primarily teach the unit using Android, but you are
              | permitted to do your project using Swift for Apple devices if you wish. But please note that we won't
              | have teaching materials available for the Swift/iPhone side of things. (But there are quite a few
              | resources for that out there on the web.)"
            """.stripMargin
          ),
          RQMCOption(
            " No, I don't have a phone",
            """
              | Ok, but you will find the unit a little harder. There are emulators that can let you run an
              | Android app on your computer or in the cloud. But it won't quite be the same as having the device in
              | your hand to play with.
            """.stripMargin
          )
        )
      ),
      RQMCQuestion(
        "Do you have a computer you can install Android Studio on. (It might be a good idea to try installing it before enrolling)",
        Seq(
          RQMCOption(
            "Yes",
            "That's good. I'd suggest getting it set up as soon as you can, as sometimes students find it can be a bit fiddly."
          ),
          RQMCOption(
            "No, but I'd like to develop for iOS, and I have a Mac with the Developer Tools",
            """
              |Ok, but you will find the unit a little harder. We primarily teach the unit using Android, but you are
              |permitted to do your project using Swift for Apple devices if you wish. But please note that we won't
              |have teaching materials available for the Swift/iPhone side of things. (But there are quite a few
              |resources for that out there on the web.)
            """.stripMargin
          ),
          RQMCOption(
            "No",
            """
              |Ok, but you will find the unit a little harder. There are cloud IDEs, such as Codenvy,
              |that you can use instead. But it might not be quite the same as having the IDE on your desktop and hooked
              |up to your phone.
            """.stripMargin
          )
        )
      ),
      RQMCQuestion(
        """
          | If you want to check whether you're happy with the unit itself, glance at the preview materials (scroll down).
          | Have a look and see what you think.
        """.stripMargin,
        Seq(
          RQMCOption(
            "Yes, it's lovely, just the course for me!",
            "Well, thank you. Of course they say flattery will get you nowhere, but we like it anyway."
          ),
          RQMCOption(
            "No, terrible, terrible!",
            """
              |Yeah, yeah, we know you only clicked that to see what the message would be! No, not really --
              |if you're not keen on the unit, that's ok, but it'd be really helpful if you'd let us know why.
            """.stripMargin
          ),
          RQMCOption(
            "Maybe I'll look at that later",
            """
              |Ok, no worries.
            """.stripMargin
          )
        )
      ),
      RQMCQuestion(
        """
          | Last thing -- just as an extra check on your tech set-up, it might be worth trying to download and install
          | Android studio before the unit starts. (As it can be quite hard for us to debug online students' home
          | computers!)
        """.stripMargin,
        Seq(
          RQMCOption(
            "Ok, I'll have a look at that sometime",
            "Great."
          ),
          RQMCOption(
            "I'm not planning on using Android Studio",
            """
              |Ok, but you will find the unit a little harder. We primarily teach the unit using Android, but you are
              |permitted to do your project using Swift for Apple devices if you wish. But please note that we won't
              |have teaching materials available for the Swift/iPhone side of things. (But there are quite a few
              |resources for that out there on the web.)
            """.stripMargin
          ),
          RQMCOption(
            "I think I'll be ok",
            """
              |Ok.
            """.stripMargin
          )
        )
      )

    ))),

    details = TeachingUnitDetails(
      backgroundImg = Some("/assets/units/cosc370/craft.jpg"),
      welcome = Some(ContentItem(
        details = ContentItemDetails(title = Some("Welcome")),
        //body = YouTubeVideo("oBBqMIQ4KI8")
        body = KalturaVideo("424421", "21338692", "0_jxujdkt9")
      ))
    ),

    assessment = Seq(
      AssessmentDescription(
        title = "Project concept",
        description = "Presentation video",
        percentage = 5,
        lo = Seq(2)
      ),
      AssessmentDescription(
        title = "Design prototype",
        description = "Video and materials",
        percentage = 20,
        lo = Seq(2, 3, 4, 5)
      ),
      AssessmentDescription(
        title = "Finished project demo",
        description = "Video and materials",
        percentage = 20,
        lo = Seq(2, 3, 4, 5)
      ),
      AssessmentDescription(
        title = "Studio feedback",
        description = "Videos",
        percentage = 5,
        lo = Seq(2, 3, 4, 5)
      ),
      AssessmentDescription(
        title = "Reflective Journal",
        description = "Written",
        percentage = 10,
        lo = Seq(2, 3, 4, 5)
      ),
      AssessmentDescription(
        title = "Quizzes",
        description = "Self-paced",
        percentage = 10,
        lo = Seq(1, 2, 3, 4, 5)
      ),
      AssessmentDescription(
        title = "Exam",
        description = "Supervised examination",
        percentage = 30,
        lo = Seq(1, 2, 3, 4, 5)
      )
    ),

    resources = Resources(
      interactivity = Seq(
        Resources.Forum,
        Resources.Slack,
        Resources.GitHub,
        Resources.GroupWork,
        Resources.Quizzes,
        Resources.VideoRecordings
      ),
      textbooks = Seq(
        Resources.Textbook(Resources.Recommended,
          title = "Designing Interactive Systems: A Comprehensive Guide to HCI, UX and Interaction Design",
          authors = "Benyan, D.",
          isbn13 = Some("9781447920113")
        ),
        Resources.Textbook(Resources.Recommended,
          title = "The Design of Everyday Things",
          authors = "Norman, D.",
          isbn13 = Some("9780465050659")
        ),
        Resources.Textbook(Resources.Referenced,
          title = "Observing the User Experience: A Practitioner's Guide to User Research",
          authors = "Goodman, E., Kuniavsky, M. and Moed, A.",
          isbn13 = Some("9780123848697"),
          edition = Some("2nd ed.")
        ),
        Resources.Textbook(Resources.Referenced,
          title = "About Face: The Essentials of Interaction Design",
          authors = "Cooper, A.",
          isbn13 = Some("9781118766576")
        ),
        Resources.Textbook(Resources.Referenced,
          title = "Research Methods in Human-Computer Interaction",
          authors = "Lazar, J., Feng, J.H. and Hochheiser, H.",
          isbn13 = Some("9780470723371")
        )
      )
    ),

    preview = Some(Preview(Seq(
      TopicSection(
        title = "1. Introduction",
        tileImage = Some("/assets/units/cosc370/1/ps3controller-small.jpg"),
        wideImage = Some("/assets/units/cosc370/1/ps3controller-small.jpg"),
        content = Seq(
          ContentItem(
            ContentItemDetails(
              title = None,
              category = ContentItemCategory.Message
            ),
            body = MarkdownText(
              """
                | Welcome to the course. In this first week, we have a few things to get through.
                | First, we all ought to introduce ourselves. During the term, you'll be working as part of a
                | design community, and next week you need to post a little video describing the "idea" of the
                | app you would like to build. So this week, let's all get to know each other.
              """.stripMargin)
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Introductions"),
              category = ContentItemCategory.Discussion
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Class Slack group"),
              category = ContentItemCategory.Slack
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = None,
              category = ContentItemCategory.Message
            ),
            body = MarkdownText(
              """
                | I also need to introduce you to some of the terminology -- you've probably heard terms like
                | "user experience", "interaction design", and "human computer interaction" before, but we should
                | sort out what's what.
              """.stripMargin)
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("UX, UI, and IxD?"),
              length = Some("45 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = None,
              category = ContentItemCategory.Message
            ),
            body = MarkdownText(
              """
                | And we also need to start introducing you to Android, the mobile development environment we're
                | going to recommend using. Really, there are some excellent Android tutorials out there on the
                | web, so we'll keep our tutorials fairly focused on what we need for this unit, and use some
                | outside tutorials to fill in the blanks.
              """.stripMargin)
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Introduction to Android"),
              length = Some("45 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Getting set up"),
              category = ContentItemCategory.ProgrammingTutorial
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Simple app to try"),
              category = ContentItemCategory.GitHub
            ),
            body = Placeholder
          )
        )
      ),
      TopicSection(
        "4. Defining the problem",
        wideImage = Some("/assets/units/cosc370/2/notepad.jpg"),
        tileImage = Some("/assets/units/cosc370/2/notepad.jpg"),
        content = Seq(
          ContentItem(
            ContentItemDetails(
              title = None,
              category = ContentItemCategory.Message
            ),
            body = MarkdownText(
              """
                | In the empathise stage, we looked at all sorts of different ways to get to understand our
                | users a bit better. But now we're faced with this problem that though we've seen a lot of things
                | and maybe feel a bit less distant from our users, nothing is organised or defined yet. We need
                | some way to sort out all this information so we start to think about what we think the
                | problem really is.
              """.stripMargin)
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Coding qualitative data"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Grounded Theory"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("How Contextual Design does it"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Affinity Diagrams"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Personas and Empathy Maps"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Task and Work Analysis"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("User Stories, Scenarios, and Requirements"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Automated analysis"),
              length = Some("10 min"),
              category = ContentItemCategory.Video
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Empathise & Define"),
              length = Some("20 questions"),
              category = ContentItemCategory.Quiz
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = None,
              category = ContentItemCategory.Message
            ),
            body = MarkdownText(
              """
                | This week, we're going to step away from Android for a moment. We're all going to have a go
                | at building an affinity diagram in the classroom, recorded with a 360 degree camera. Partly
                | so we can have a go at affinity diagrams, but mostly so we can play with 360 degree video next week!
              """.stripMargin)
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Affinity diagramming (in-class)"),
              category = ContentItemCategory.ActiveTutorial
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Affinity diagramming with online tools (online)"),
              category = ContentItemCategory.ProgrammingTutorial
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("nVivo"),
              category = ContentItemCategory.ProgrammingTutorial
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = None,
              category = ContentItemCategory.Message
            ),
            body = MarkdownText(
              """
                | And as usual, here are few interesting things from around the web about design
              """.stripMargin)
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("The $300 million button"),
              category = ContentItemCategory.Web
            ),
            body = Placeholder
          ),
          ContentItem(
            ContentItemDetails(
              title = Some("Lego engineered a remarkable turnaround of its business. How did it happen?"),
              category = ContentItemCategory.Web
            ),
            body = Placeholder
          )



        )
      )
    )))

  )


  val units = Map(
    "cosc370" -> COSC370
  )

}

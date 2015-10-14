package au.edu.une.cs.cstweaked

import info.tweaked.Unit
import info.tweaked.Term.{T1, T2, T3}
import info.tweaked.Prerequisite._

/**
 * Holds definitions of COSC units
 */
object CoscUnits {

  val AMTH140 = Unit(
    "AMTH140",
    name = Some("Discrete Mathematics"),
    terms= T2
  )

  val AMTH150 = Unit(
    "AMTH150",
    terms=T1
  )

  val MTHS120 = Unit(
    code = "MTHS120",
    name = Some("Calculus and Linear Algebra 1"),
    terms = T1
  )

  val MTHS130 = Unit(
    code = "MTHS130",
    name = Some("Calculus and Linear Algebra 2"),
    terms = T2 ++ T3,
    require = MTHS120
  )

  val MTHS110 = Unit(
    code = "MTHS110",
    name = Some("Quantitative Skills with Applications"),
    terms = T1 ++ T2
  )

  val MTHS100 = Unit(
    code = "MTHS100",
    name = Some("Introduction to Quantitative Skills"),
    terms = T1 ++ T3
  )

  val STAT100 = Unit(
    code = "STAT100",
    name = Some("Introduction to Statistical Modelling"),
    terms = T2 ++ T3
  )

  val STAT210 = Unit(
    code = "STAT210",
    name = Some("Statistical Modelling and Experimental Design"),
    terms = T1,
    require = STAT100 or AMTH150
  )

  val STAT330 = Unit(
    code = "STAT330",
    name = Some("Statistical Learning"),
    terms = T1,
    require = STAT210
  )

  val COSC110 = Unit(
    "COSC110",
    name=Some("Intro to Programming and the UNIX environment"),
    terms=T1 ++ T2
  )

  val COSC120 = Unit(
    code = "COSC120",
    name = Some("Object oriented programming"),
    terms = T2 ++ T1,
    require = COSC110 or AMTH150
  )
  val COSC210 = Unit(
    code = "COSC210",
    name = Some("Database Management Systems"),
    terms = Seq(T1),
    require = COSC110 or AMTH150
  )
  val COSC220 = Unit(
    code = "COSC220",
    name = Some("Software Engineering Studio"),
    terms = T2,
    require = COSC120
  )
  val COSC230 = Unit(
    code = "COSC230",
    name = Some("Data Structures and Algorithms"),
    terms = T1,
    require = AMTH140 and COSC120
  )
  val COSC240 = Unit(
    code = "COSC240",
    name = Some("Operating Systems"),
    terms = T2,
    require = COSC120
  )
  val COSC250 = Unit(
    code = "COSC250",
    name = Some("Programming Paradigms"),
    terms = T1,
    require = COSC120
  )
  val COSC260 = Unit(
    code = "COSC260",
    name = Some("Web Programming"),
    terms = T2,
    require = COSC110 and COSC120
  )
  val COSC310 = Unit(
    code = "COSC310",
    name = Some("Software Project Management"),
    terms = T1,
    require = COSC220 and minCP(72) //96
  )
  val COSC320 = Unit(
    code = "COSC320",
    name = Some("Information Technology Project"),
    terms = T1 ++ T2,
    require = COSC220 and COSC310 and minCP(96) // 24
  )
  val COSC330 = Unit(
    code = "COSC330",
    name = Some("Parallel and Distributed Computing"),
    terms = T2,
    require = COSC230 or COSC240
  )
  val COSC340 = Unit(
    code = "COSC340",
    name = Some("Computer Networks and Information Security"),
    terms = T1,
    require = AMTH140 and COSC240 and minCP(72)
  )
  val COSC350 = Unit(
    code = "COSC350",
    name = Some("Artificial Intelligence"),
    terms = T2,
    require = COSC230
  )



  val COSC360 = Unit(
    code = "COSC360",
    name = Some("Advanced Web Programming"),
    terms = T2,
    require = COSC260 and COSC210 and COSC120
  )
  val COSC370 = Unit(
    code = "COSC370",
    name = Some("User Experience and Interaction Design"),
    terms = T1,  // TODO: Change form
    require = COSC220 and minCP(72)
  )
  val COSC380 = Unit(
    code = "COSC380",
    name = Some("Advanced Computational Science"),
    terms = T2,  // TODO: Change form
    require = (AMTH150 or COSC110) and MTHS120 and minCP(48)
  )


}

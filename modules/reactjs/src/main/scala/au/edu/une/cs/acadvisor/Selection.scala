package au.edu.une.cs.acadvisor

import au.edu.une.cs.cstweaked.CoscUnits._
import au.edu.une.cs.cstweaked.Courses
import info.tweaked.Plan
import info.tweaked.Term.{T1, T2, T3}
import info.tweaked.Unit._


object Selection {

  val terms = Seq(T1, T2, T3)

  var unitsPerTerm:Int = 4

  var rules = Courses.bCompSD

  var plan:Plan = Plan("BComp Dual Major MTH110 c",
    T1(COSC110, MTHS110, Elective, Listed),
    T2(AMTH140, COSC120, STAT100, MTHS120), T3(),
    T1(COSC210, COSC230, COSC250, STAT210),
    T2(COSC220, COSC240, COSC260, COSC330), T3(),
    T1(COSC310, COSC370, COSC340, STAT330),
    T2(COSC320, COSC360, COSC380, COSC350), T3()
  )


  var startingTerm = T1

}

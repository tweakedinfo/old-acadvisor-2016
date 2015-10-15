package au.edu.une.cs.cstweaked

import info.tweaked.TUnit._
import info.tweaked.Prerequisite._
import info.tweaked.Standing._
import CoscUnits._

object CompUnits {


  val math101 = old("MATH101", "Algebra and Differential Calculus")
  val math102 = old("MATH102", "Integral Calculus, Differential Equations and Introductory Statistics")
  val math120 = old("MATH120", "Introductory Mathematical Methods in Science and Economics")

  val comp131 = old("COMP131", "Introduction to Programming and Professional Practice")
  val comp132 = old("COMP132", "Intermediate Programming")
  val comp170 = old("COMP170", "The Linux/Unix Programming Environment")

  val comp280 = old("COMP280", "Operating Systems")
  val comp284 = old("COMP284", "Software Engineering")
  val comp389 = old("COMP389", "Databases")
  val comp393 = old("COMP393", "Software Project Management")
  val comp395 = old("COMP395", "Information Technology Project")

  val oldMathUnits = Seq(
    math101, math102, math120
  )

  val oldCompUnits = Seq(
    comp131, comp132, comp170,
    comp280, comp284, comp389, comp393, comp395
  )

  val currentUnits = Seq(
    AMTH140
  )

}

package au.edu.une.cs.cstweaked

import info.tweaked.model.unit.TeachingUnit._
import info.tweaked.model.plan.Standing
import Standing._
import CoscUnits._

object CompUnits {


  val math101 = old("MATH101", "Algebra and Differential Calculus")
  val math102 = old("MATH102", "Integral Calculus, Differential Equations and Introductory Statistics")
  val math120 = old("MATH120", "Introductory Mathematical Methods in Science and Economics")

  val comp100 = old("COMP100", "Introductory Information Technology")
  val comp131 = old("COMP131", "Introduction to Programming and Professional Practice")
  val comp132 = old("COMP132", "Intermediate Programming")
  val comp170 = old("COMP170", "The Linux/Unix Programming Environment")

  val comp280 = old("COMP280", "Operating Systems")
  val comp283 = old("COMP283", "Computer Architecture and Assembler")
  val comp284 = old("COMP284", "Software Engineering")
  val comp285 = old("COMP285", "C/C++ for Scientists and Engineers")
  val comp286 = old("COMP286", "Systems Analysis and Design")
  val comp287 = old("COMP287", "GUI Programming and Interaction Design")
  val comp290 = old("COMP290", "Internet Security")

  val comp303 = old("COMP303", "Decision Support Systems")
  val comp309 = old("COMP309", "Parallel and Distributed Computing")
  val comp313 = old("COMP313", "Data Mining")
  val comp315 = old("COMP315", "Web and Internet Programming")
  val comp320 = old("COMP320", "Computer Networks")
  val comp389 = old("COMP389", "Databases")
  val comp391 = old("COMP391", "Advanced Web Technologies")
  val comp393 = old("COMP393", "Software Project Management")
  val comp395 = old("COMP395", "Information Technology Project")

  val oldMathUnits = Seq(
    math101, math102, math120
  )

  val oldCompUnits = Seq(
    comp131, comp132, comp170,
    comp280, comp284, comp287, comp290,
    comp303, comp313, comp315, comp320, comp389, comp391, comp393, comp395
  )

  val currentUnits = Seq(
    AMTH140
  )

}

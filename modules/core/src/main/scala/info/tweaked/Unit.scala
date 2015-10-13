package info.tweaked

import scala.language.implicitConversions
import Term._

/** A teaching unit */
case class Unit(
   name:String,
   terms: Seq[Term],
   require: Prerequisite = Prerequisite.Yes,
   restrict: Prerequisite = Prerequisite.No,
   cp:Int = 6
 )


object Unit {
  implicit def prereq(u:Unit):Prerequisite = Prerequisite.Contains(u)

  /** An unlisted unit */
  def x(s:String) = Unit(s, T1 ++ T2 ++ T3)

  val any = x("Any")
  val Elective = x("Elective")
  val Listed = x("Listed")
  val Prescribed = x("Prescribed")

}


package info.tweaked

import scala.language.implicitConversions
import Term._

/** A teaching unit */
case class Unit(
   code:String,
   name:Option[String] = None,
   link:Option[String] = None,
   terms: Seq[Term] = Seq.empty,
   require: Prerequisite = Prerequisite.Yes,
   restrict: Prerequisite = Prerequisite.No,
   description:Option[String] = None,
   languages:Seq[String] = Seq.empty,
   cp:Int = 6
)


object Unit {
  implicit def prereq(u:Unit):Prerequisite = Prerequisite.Contains(u)

  /** An unlisted unit */
  def x(s:String) = Unit(s, terms=T1 ++ T2 ++ T3)

  def old(c:String, s:String) = Unit(c, terms=T1 ++ T2 ++ T3, name=Some(s))

  val Unchosen = x("Unchosen")

  val any = x("Any")
  val Elective = x("Elective")
  val Listed = x("Listed")
  val Prescribed = x("Prescribed")

}

case class NamedSet(name:String, units:Seq[Unit],  notes:Seq[String] = Seq.empty)
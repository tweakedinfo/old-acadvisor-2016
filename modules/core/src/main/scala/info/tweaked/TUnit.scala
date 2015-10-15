package info.tweaked

import info.tweaked.Problem.{AlreadyDone, Restricted, PrerequisiteNotMet}

import scala.language.implicitConversions
import Term._

import scala.util.Random

/** A teaching unit */
case class TUnit(
   code:String,
   name:Option[String] = None,
   link:Option[String] = None,
   terms: Seq[Term] = Seq.empty,
   require: Prerequisite = Prerequisite.Yes,
   restrict: Prerequisite = Prerequisite.No,
   description:Option[String] = None,
   languages:Seq[String] = Seq.empty,
   fuzz:String = "",
   cp:Int = 6
) {
  def prereqNotMet(complete:Seq[TUnit]) = if (require.apply(complete)) None else Some(PrerequisiteNotMet(this))

  def restricted(inclusive:Seq[TUnit]) = if (restrict.apply(inclusive)) Some(Restricted(this)) else None

  def alreadyDone(complete:Seq[TUnit]) = if (complete.contains(this)) Some(AlreadyDone(this)) else None

  def validFor(past:Seq[TUnit], inclusive:Seq[TUnit]) = {
    prereqNotMet(past) ++ restricted(inclusive) ++ alreadyDone(past)
  }
}


object TUnit {
  implicit def prereq(u:TUnit):Prerequisite = Prerequisite.Contains(u)

  /** An unlisted unit */
  def x(s:String) = TUnit(s, terms=T1 ++ T2 ++ T3, fuzz = Random.nextString(8))

  def old(c:String, s:String) = TUnit(c, terms=T1 ++ T2 ++ T3, name=Some(s))

  val Unchosen = x("Unchosen")

  val any = x("Any")
  def elective = x("Elective")
  val Listed = x("Listed")
  val Prescribed = x("Prescribed")

}

case class NamedSet(name:String, units:Seq[TUnit],  notes:Seq[String] = Seq.empty)
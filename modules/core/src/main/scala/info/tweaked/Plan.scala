package info.tweaked

import info.tweaked.Problem.{AlreadyDone, Restricted, PrerequisiteNotMet, WrongTerm}

/**
 * A programme of study
 */
case class Plan(name:String, terms:Seq[Termful], standing:Seq[TUnit] = Seq.empty) {

  def printCheck() = {
    println(name)
    println("---")
    for (p <- problems) println(p)
    println("")
  }

  def problems:Seq[Problem] = {
    import scala.collection.mutable

    val buf: mutable.Buffer[TUnit] = mutable.Buffer.empty
    val probs: mutable.Buffer[Problem] = mutable.Buffer.empty

    for {
      (termful, i) <- terms.zipWithIndex
      problem <- termful.problems(i, terms:_*)
    } yield problem
  }

  def contains(unit:TUnit) = standing.contains(unit) || terms.exists(_.contains(unit))

  def until(unitChoice: UnitChoice) = terms.takeWhile(!_.contains(unitChoice))

  def termful(unitChoice: UnitChoice) = terms.find(_.contains(unitChoice))
}

class UnitChoice(val term:Term, var unit:TUnit) {

  def isEmpty = unit == TUnit.Unchosen

  def termInvalid = if (unit.terms.contains(term)) None else Some(WrongTerm(this))

  def prereqNotMet(complete:Seq[TUnit]) = unit.prereqNotMet(complete)

  def restricted(inclusive:Seq[TUnit]) = unit.restricted(inclusive)

  def alreadyDone(complete:Seq[TUnit]) = unit.alreadyDone(complete)

  def problemsFor(unit:TUnit, complete:Seq[Termful], current:Termful):Seq[Problem] = {
    val completeU = complete.flatMap(_.units)
    unit.prereqNotMet(completeU).toSeq ++ unit.alreadyDone(completeU) ++ unit.restricted(completeU ++ current.units)
  }

  def problems(complete:Seq[TUnit], inclusive:Seq[TUnit]):Seq[Problem] = {
    termInvalid.toSeq ++ prereqNotMet(complete) ++ alreadyDone(complete) ++ restricted(inclusive)
  }

}

object UnitChoice {
  def apply(term:Term, unit:TUnit) = new UnitChoice(term, unit)
}


/** A Term with a selection of Units to take in that Term */
case class Termful(term:Term, unitChoices:Seq[UnitChoice]) {

  def units = unitChoices.map(_.unit)

  def contains(unit:TUnit) = unitChoices.exists(_.unit == unit)

  def contains(unitChoice: UnitChoice) = unitChoices.contains(unitChoice)

  def problems(index:Int, terms:Termful*) = {
    val complete = terms.slice(0, index).flatMap(_.units)
    val inclusive = complete ++ terms(index).units

    unitChoices.foldLeft[Seq[Problem]](Seq.empty)(_ ++ _.problems(complete, inclusive))
  }
}


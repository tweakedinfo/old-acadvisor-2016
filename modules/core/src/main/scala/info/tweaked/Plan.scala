package info.tweaked

import info.tweaked.Problem.{Restricted, PrerequisiteNotMet, WrongTerm}

/**
 * A programme of study
 */
case class Plan(name:String, terms:Termful*) {

  def printCheck() = {
    println(name)
    println("---")
    for (p <- problems) println(p)
    println("")
  }

  def problems:Seq[Problem] = {
    import scala.collection.mutable

    val buf: mutable.Buffer[Unit] = mutable.Buffer.empty
    val probs: mutable.Buffer[Problem] = mutable.Buffer.empty

    for {
      (termful, i) <- terms.zipWithIndex
      problem <- termful.problems(i, terms:_*)
    } yield problem
  }

  def contains(unit:Unit) = terms.contains(unit)

}

case class UnitChoice(term:Term, var unit:Unit) {

  def isEmpty = unit == Unit.Unchosen

  def termInvalid = if (unit.terms.contains(term)) None else Some(WrongTerm(this))

  def prereqNotMet(complete:Seq[Unit]) = if (unit.require.apply(complete)) None else Some(PrerequisiteNotMet(this))

  def restricted(inclusive:Seq[Unit]) = if (unit.restrict.apply(inclusive)) Some(Restricted(this)) else None

  def problems(complete:Seq[Unit], inclusive:Seq[Unit]):Seq[Problem] = {
    termInvalid.toSeq ++ prereqNotMet(complete) ++ restricted(inclusive)
  }

}


/** A Term with a selection of Units to take in that Term */
case class Termful(term:Term, unitChoices:Seq[UnitChoice]) {

  def units = unitChoices.map(_.unit)

  def contains(unit:Unit) = unitChoices.exists(_.unit == unit)

  def problems(index:Int, terms:Termful*) = {
    val complete = terms.slice(0, index).flatMap(_.units)
    val inclusive = complete ++ terms(index).units

    unitChoices.foldLeft[Seq[Problem]](Seq.empty)(_ ++ _.problems(complete, inclusive))
  }
}


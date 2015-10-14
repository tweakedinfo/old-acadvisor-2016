package info.tweaked

trait Problem

object Problem {

  case class WrongTerm(unitChoice: UnitChoice) extends Problem
  case class PrerequisiteNotMet(unit: TUnit) extends Problem
  case class Restricted(unit: TUnit) extends Problem
  case class AlreadyDone(unit:TUnit) extends Problem

}
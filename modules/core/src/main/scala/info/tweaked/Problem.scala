package info.tweaked

trait Problem

object Problem {

  case class WrongTerm(unitChoice: UnitChoice) extends Problem
  case class PrerequisiteNotMet(unitChoice: UnitChoice) extends Problem
  case class Restricted(unitChoice: UnitChoice) extends Problem

}
package au.edu.une.cs.acadvisor

import au.edu.une.cs.cstweaked.CoscUnits._
import au.edu.une.cs.cstweaked.{CompUnits, Courses}
import info.tweaked.{Prerequisite, TUnit, UnitChoice, Plan}
import info.tweaked.Term.{T1, T2, T3}
import info.tweaked.TUnit._
import scala.collection.mutable

sealed trait Selection
case object NoSel extends Selection
case class SelUnit(u:TUnit) extends Selection
case class SelUnitChoice(uc:UnitChoice) extends Selection

object Selection {

  val terms = Seq(T1, T2, T3)

  val taken = mutable.Set.empty[TUnit]
  def toggleTaken(u:TUnit) = {
    if (taken.contains(u)) taken.remove(u) else taken.add(u)
    val standing = rules.standing(taken.toSeq:_*)
    plan = plan.copy(standing = standing)
    Main.rerender()
  }
  def standing = plan.standing


  val courses = Seq(Courses.bCompSD, Courses.bCompAM, Courses.bCompDual)

  var rules = Courses.bCompSD

  def setCourse(name:String) = {
    for {
      c <- courses.find(_.name == name)
    } rules = c
    Main.rerender()
  }

  def checkRuleAgainstPlan(r:Prerequisite) = r.apply(plan.units)

  var unitsPerTerm:Int = 4

  def setUnitsPerTerm(i:Int) = {
    unitsPerTerm = i
    plan = genPlan
    Main.rerender()
  }

  var startingTerm = T1
  def setStartingTerm(i:Int) = {
    startingTerm = terms(i)
    plan = genPlan
    Main.rerender()
  }

  var selection:Selection = NoSel

  def setSelectedUC(u:TUnit) = {
    selection match {
      case SelUnitChoice(uc) => {
        uc.unit = u
        selection = NoSel
        Main.rerender()
      }
      case _ => {}
    }
  }

  def selected(u:TUnit) = selection == SelUnit(u)
  def selected(uc:UnitChoice) = selection == SelUnitChoice(uc)

  def selectable(u:TUnit) = selection match {
    case SelUnitChoice(uc) => u.terms.contains(uc.term) && !plan.contains(u)
    case _ => !plan.contains(u)
  }

  def selectable(uc:UnitChoice) = selection match {
    case SelUnit(u) => u.terms.contains(uc.term)
    case _ => true
  }

  def select(u:TUnit) = {
    if (selected(u)) {
      selection = NoSel
      Main.rerender()
    } else if (selectable(u)) {
      selection match {
        case SelUnitChoice(uc) => {
          uc.unit = u
          selection = NoSel
        }
        case _ => selection = SelUnit(u)
      }
      Main.rerender()
    }
  }

  def select(uc:UnitChoice) = {
    if (selected(uc)) {
      selection = NoSel
      Main.rerender()
    } else if (selectable(uc)) {
      selection match {
        case SelUnit(u) => {
          uc.unit = u
          selection = NoSel
        }
        case _ => selection = SelUnitChoice(uc)
      }
      Main.rerender()
    }
  }

  def validSel(uc:UnitChoice) = {
    selection match {
      case SelUnit(u) => {
        val upto = plan.until(uc)
        val termful = plan.find(uc)
        termful match {
          case Some(t) => uc.problemsFor(u, plan.standing, upto, t).isEmpty
          case _ => false
        }
      }
      case _ => false
    }
  }

  def validSel(u:TUnit) = {
    selection match {
      case SelUnitChoice(uc) => {
        val upto = plan.until(uc)
        val termful = plan.find(uc)
        termful match {
          case Some(t) => uc.problemsFor(u, plan.standing, upto, t).isEmpty
          case _ => false
        }
      }
      case _ => false
    }
  }

  var plan:Plan = genPlan

  def genPlan = {
    val numTerms = (12 / unitsPerTerm) * 3

    val pad = if (startingTerm == T1) {
      Seq.empty
    } else if (startingTerm == T2) {
      Seq(T1())
    } else Seq(T1(), T2())

    Plan("My plan",
      terms = pad ++ (for (i <- 0 until numTerms) yield {
        terms((i + pad.size) % terms.size)(
          (for(i <- 0 until unitsPerTerm) yield Unchosen):_*
        )
      })
    )
  }

  /**
   * Greedily attempts to pack in all units as early as legal
   */
  def pack(units:Seq[TUnit]) = {
    selection = NoSel
    for {
      t <- plan.terms
      uc <- t.unitChoices if uc.unit == TUnit.Unchosen
      past = plan.until(uc).flatMap(_.units) ++ plan.standing
    } {
      val current = t.units
      val inclusive = past ++ current
      val candidate = rules.units.find { u =>
        !current.contains(u) &&
        u.validFor(past, inclusive).isEmpty &&
        u.terms.contains(uc.term)
      }
      for { u <- candidate } uc.unit = u
    }
    Main.rerender()
  }

}

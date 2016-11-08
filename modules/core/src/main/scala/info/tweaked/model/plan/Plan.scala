package info.tweaked.model.plan

import info.tweaked.model.unit.{Term, TeachingUnit, Offering}

/**
  * Created by wbilling on 30/10/2016.
  */
case class Plan(name:String, selection:Map[Term, Seq[TeachingUnit]] = Map.empty) {

  lazy val allUnits:Seq[TeachingUnit] = selection.values.flatten.toSeq

  def unitInPlan(u:TeachingUnit):Boolean = {
    selection.valuesIterator.exists(_.contains(u))
  }

  def beforeStartOf(t:Term):Map[Term, Seq[TeachingUnit]] = {
    selection.filterKeys(_.beforeStartOf(t))
  }

}

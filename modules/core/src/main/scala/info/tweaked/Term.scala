package info.tweaked

import scala.language.implicitConversions

/** A teaching period */
case class Term(name:String) {
  def apply(units:Unit*) = Termful(this, units.map(UnitChoice(this, _)))
}

object Term {
  implicit def toSeq(t:Term):Seq[Term] = Seq(t)

  val T1 = Term("T1")
  val T2 = Term("T2")
  val T3 = Term("T3")
}


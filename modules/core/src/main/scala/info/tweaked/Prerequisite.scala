package info.tweaked

/**
 * A condition that is true or false, depending on the units the student has already taken
 */
trait Prerequisite {
  def apply(units: Seq[TUnit]): Boolean

  def or (p:Prerequisite) = Prerequisite.Or(Seq(this, p))

  def and (p:Prerequisite) = Prerequisite.And(Seq(this, p))

  def contains(u:TUnit):Boolean = false
}

object Prerequisite {

  /** A condition that is always true */
  case object Yes extends Prerequisite {
    def apply(units:Seq[TUnit]) = true
  }

  /** A condition that is always false */
  case object No extends Prerequisite {
    def apply(units:Seq[TUnit]) = false
  }

  /** A condition that is true if any of its children are */
  case class Or(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[TUnit]) = prereqs.foldLeft(false)(_ || _.apply(units))
  }

  /** A condition that is true if all of its children are */
  case class And(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[TUnit]) = prereqs.forall(_.apply(units))
  }

  /** A condition that the units so far contains a minimum number of credit points */
  case class minCP(cps:Int) extends Prerequisite {
    def apply(units:Seq[TUnit]) = units.foldLeft(0)(_ + _.cp) >= cps
  }

  /** A condition that the units so far includes a set of units */
  case class Contains(required: TUnit*) extends Prerequisite {
    def apply(units:Seq[TUnit]) = required.diff(units).isEmpty

    override def contains(u:TUnit) = required.contains(u)
  }

  case class NumFrom(num:Int, units:TUnit*) extends Prerequisite {
    def apply(units:Seq[TUnit]) = units.intersect(units).size >= num

    override def contains(u:TUnit) = units.contains(u)
  }

}

case class NamedRule(name:String, rule:Prerequisite, extra:Seq[TUnit] = Seq.empty, notes:Seq[String] = Seq.empty) {
  def contains(u:TUnit) = rule.contains(u) || extra.contains(u)
}

case class Course(name:String, rules:Seq[NamedRule], coloringRule:(TUnit) => String = _ => "")
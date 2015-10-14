package info.tweaked

/**
 * A condition that is true or false, depending on the units the student has already taken
 */
trait Prerequisite {
  def apply(units: Seq[Unit]): Boolean

  def or (p:Prerequisite) = Prerequisite.Or(Seq(this, p))

  def and (p:Prerequisite) = Prerequisite.And(Seq(this, p))

  def contains(u:Unit):Boolean = false
}

object Prerequisite {

  /** A condition that is always true */
  case object Yes extends Prerequisite {
    def apply(units:Seq[Unit]) = true
  }

  /** A condition that is always false */
  case object No extends Prerequisite {
    def apply(units:Seq[Unit]) = false
  }

  /** A condition that is true if any of its children are */
  case class Or(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[Unit]) = prereqs.foldLeft(false)(_ || _.apply(units))
  }

  /** A condition that is true if all of its children are */
  case class And(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[Unit]) = prereqs.forall(_.apply(units))
  }

  /** A condition that the units so far contains a minimum number of credit points */
  case class minCP(cps:Int) extends Prerequisite {
    def apply(units:Seq[Unit]) = units.foldLeft(0)(_ + _.cp) >= cps
  }

  /** A condition that the units so far includes a set of units */
  case class Contains(required: Unit*) extends Prerequisite {
    def apply(units:Seq[Unit]) = required.diff(units).isEmpty

    override def contains(u:Unit) = required.contains(u)
  }

  case class NumFrom(num:Int, units:Unit*) extends Prerequisite {
    def apply(units:Seq[Unit]) = units.intersect(units).size >= num

    override def contains(u:Unit) = units.contains(u)
  }

}

case class NamedRule(name:String, rule:Prerequisite, extra:Seq[Unit] = Seq.empty, notes:Seq[String] = Seq.empty) {
  def contains(u:Unit) = rule.contains(u) || extra.contains(u)
}

case class Course(name:String, rules:Seq[NamedRule], coloringRule:(Unit) => String = _ => "")
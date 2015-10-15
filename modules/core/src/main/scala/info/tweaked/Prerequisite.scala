package info.tweaked

/**
 * A condition that is true or false, depending on the units the student has already taken
 */
trait Prerequisite {
  def apply(units: Seq[TUnit]): Boolean

  def or (p:Prerequisite) = Prerequisite.Or(Seq(this, p))

  def and (p:Prerequisite) = Prerequisite.And(Seq(this, p))

  def stringify:String

  def units:Seq[TUnit] = Seq.empty

  def contains(u:TUnit):Boolean = false
}

object Prerequisite {

  /** A condition that is always true */
  case object Yes extends Prerequisite {
    def apply(units:Seq[TUnit]) = true

    def stringify = "Yes"
  }

  /** A condition that is always false */
  case object No extends Prerequisite {
    def apply(units:Seq[TUnit]) = false

    def stringify = "No"
  }

  /** A condition that is true if any of its children are */
  case class Or(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[TUnit]) = prereqs.foldLeft(false)(_ || _.apply(units))

    def stringify = prereqs.map(_.stringify).mkString("(", " or ", ")")
  }

  /** A condition that is true if all of its children are */
  case class And(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[TUnit]) = prereqs.forall(_.apply(units))

    override def units = prereqs.flatMap(_.units)

    def stringify = prereqs.map(_.stringify).mkString("(", " and ", ")")
  }

  /** A condition that the units so far contains a minimum number of credit points */
  case class minCP(cps:Int) extends Prerequisite {
    def apply(units:Seq[TUnit]) = units.foldLeft(0)(_ + _.cp) >= cps

    def stringify = s"$cps credit points"
  }

  /** A condition that the units so far includes a set of units */
  case class Contains(required: TUnit*) extends Prerequisite {
    def apply(units:Seq[TUnit]) = required.diff(units).isEmpty

    override def contains(u:TUnit) = required.contains(u)

    override def units = required

    def stringify = required.map(_.code).mkString("(", ", ", ")")
  }

  case class NumFrom(num:Int, required:TUnit*) extends Prerequisite {
    def apply(units:Seq[TUnit]) = required.intersect(units).size >= num

    override def contains(u:TUnit) = required.contains(u)

    override def units = required

    def stringify = s"$num from ${required.map(_.code).mkString("(", ", ", ")")}"
  }

  case class NumSatisfying(num:Int, check: (TUnit) => Boolean, condStr:String = "satisfying a condition") extends Prerequisite {
    def apply(units:Seq[TUnit]) = units.count(check) >= num

    def stringify = s"$num units $condStr"
  }


}

case class NamedRule(name:String, rule:Prerequisite, extra:Seq[TUnit] = Seq.empty, notes:Seq[String] = Seq.empty) {
  def contains(u:TUnit) = rule.contains(u) || extra.contains(u)
}

case class Course(name:String, rules:Seq[NamedRule], coloringRule:(TUnit) => String = _ => "", standing: Standing = Standing.Empty) {
  def units = rules.flatMap(_.rule.units)
}
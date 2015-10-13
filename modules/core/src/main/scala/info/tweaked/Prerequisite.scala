package info.tweaked

/**
 * A condition that is true or false, depending on the units the student has already taken
 */
trait Prerequisite {
  def apply(units: Seq[Unit]): Boolean
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
  }

}
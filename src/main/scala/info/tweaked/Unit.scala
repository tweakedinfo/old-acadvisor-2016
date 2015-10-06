package info.tweaked

import scala.language.implicitConversions

case class Termful(term:Term, units:Seq[Unit])

case class Plan(name:String, terms:Termful*) {

  def printCheck = {
    println(name)
    println("---")
    for (p <- problems) println(p)
    println("")
  }

  def problems:Seq[String] = {
    import scala.collection.mutable

    val buf:mutable.Buffer[Unit] = mutable.Buffer.empty
    val probs:mutable.Buffer[String] = mutable.Buffer.empty
    for (termful <- terms) {
      for (unit <- termful.units) {
        if (!unit.terms.contains(termful.term)) {
          probs.append(s"${unit.name} is not offered in ${termful.term.toString}")
        }
        if (!unit.require.predicate(buf.toSeq)) {
          probs.append(s"${unit.name} prerequisites have not been met")
        }
      }
      buf.append(termful.units:_*)
    }
    probs.toSeq
  }
}

trait Term {
  def apply(units:Unit*) = Termful(this, units)
}

object T1 extends Term { override def toString = "T1"}
object T2 extends Term { override def toString = "T2"}
object T3 extends Term { override def toString = "T3"}

object Term {
  implicit def toSeq(t:Term) = Seq(t)
}

trait Prerequisite {
  def apply(units: Seq[Unit]): Boolean
}

object Prerequisite {
  class Or(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[Unit]) = prereqs.foldLeft(false)(_ || _.apply(units))
  }

  class And(prereqs:Seq[Prerequisite]) extends Prerequisite {
    def apply(units:Seq[Unit]) = prereqs.forall(_.apply(units))
  }

  class minCP(cps:Int) extends Prerequisite {
    def apply(units:Seq[Unit]) = units.foldLeft(0)(_ + _.cp) >= cps
  }

}


class Prereq(val predicate: (Seq[Unit]) => Boolean) {
  def or(p:Prereq) = new Prereq(units => this.predicate(units) || p.predicate(units))
  def and(p:Prereq)= new Prereq(units => this.predicate(units) && p.predicate(units))
}

object Prereq {
  val yes = new Prereq(units => true)
  val no = new Prereq(units => false)
}


object Unit {
  implicit def prereq(u:Unit) = new Prereq(units => units.contains(u))
}

case class Unit(
               name:String,
               terms: Seq[Term],
               require: Prereq = Prereq.yes,
               restrict: Prereq = Prereq.no,
               cp:Int = 6
)



object BComp {
  def cp(i:Int) = new Prereq(units => units.foldLeft(0)(_ + _.cp) >= i)

  def x(s:String) = Unit(s, T1 ++ T2 ++ T3)

  val any = Unit("Any", T1 ++ T2 ++ T3)

  val Elective = Unit("Elective", T1 ++ T2 ++ T3)
  val Listed = Unit("Listed", T1 ++ T2 ++ T3)
  val Prescribed = Unit("Prescribed", T1 ++ T2 ++ T3)

  val AMTH140 = Unit("AMTH140", T2)

  val AMTH150 = Unit("AMTH150", T1)

  val MTHS120 = Unit(
    name = "MTHS120",
    terms = T1
  )
  val MTHS121 = Unit(
    name = "MTHS121",
    terms = T2
  )

  val MTHS130 = Unit(
    name = "MTHS130",
    terms = T2 ++ T3,
    require = MTHS120 or MTHS121
  )

  val MTHS110 = Unit(
    name = "MTHS110",
    terms = T1 ++ T2
  )

  val MTHS100 = Unit(
    name = "MTHS100",
    terms = T1 ++ T3
  )

  val STAT100 = Unit(
    name = "STAT100",
    terms = T2 ++ T3
  )

  val STAT210 = Unit(
    name = "STAT210",
    terms = T1,
    require = STAT100 or AMTH150
  )

  val STAT330 = Unit(
    name = "STAT330",
    terms = T1,
    require = STAT210
  )

  val COSC110 = Unit("COSC110", T1 ++ T2)

  val COSC120 = Unit(
    name = "COSC120",
    terms = T2 ++ T1,
    require = COSC110 or AMTH150
  )
  val COSC210 = Unit(
    name = "COSC210",
    terms = Seq(T1),
    require = COSC110 or AMTH150
  )
  val COSC220 = Unit(
    name = "COSC220",
    terms = T2,
    require = COSC120
  )
  val COSC230 = Unit(
    name = "COSC230",
    terms = T1,
    require = AMTH140 and COSC120
  )
  val COSC240 = Unit(
    name = "COSC240",
    terms = T2,
    require = COSC120
  )
  val COSC250 = Unit(
    name = "COSC250",
    terms = T1,
    require = COSC120
  )
  val COSC260 = Unit(
    name = "COSC260",
    terms = T2,
    require = COSC110 and COSC120
  )
  val COSC310 = Unit(
    name = "COSC310",
    terms = T1,
    require = COSC220 and cp(72) //96
  )
  val COSC320 = Unit(
    name = "COSC320",
    terms = T1 ++ T2,
    require = COSC220 and COSC310 and cp(96) // 24
  )
  val COSC330 = Unit(
    name = "COSC330",
    terms = T2,  // TODO: change form
    require = COSC230 or COSC240
  )
  val COSC340 = Unit(
    name = "COSC340",
    terms = T1,
    require = AMTH140 and COSC240 and cp(72)
  )
  val COSC350 = Unit(
    name = "COSC350",
    terms = T2,
    require = COSC230
  )



  val COSC360 = Unit(
    name = "COSC360",
    terms = T2,
    require = COSC260 and COSC210 and COSC120
  )
  val COSC370 = Unit(
    name = "COSC370",
    terms = T1,  // TODO: Change form
    require = COSC220 and cp(72)
  )
  val COSC380 = Unit(
    name = "COSC380",
    terms = T2,  // TODO: Change form
    require = (AMTH150 or COSC110) and (MTHS120 or MTHS121) and cp(48)
  )

}

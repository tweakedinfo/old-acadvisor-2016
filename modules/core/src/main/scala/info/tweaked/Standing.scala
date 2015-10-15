package info.tweaked

trait Standing {
  def apply(units:TUnit*):Seq[TUnit]
}

object Standing {

  case object Empty extends Standing {
    def apply(units:TUnit*) = Seq.empty
  }

  case class All(standing:Standing*) extends Standing {
    def apply(units:TUnit*) = standing.flatMap(_.apply(units:_*))
  }

  case class Filter(condition:(TUnit) => Boolean) extends Standing {
    def apply(units:TUnit*) = units.filter(condition)
  }

  case class FilterReplace(condition:(TUnit) => Boolean, replace:(TUnit) => TUnit) extends Standing {
    def apply(units:TUnit*) = for { u <- units } yield if(condition(u)) u else replace(u)
  }

  case class Grant(rule:Prerequisite, give:TUnit*) extends Standing {
    def apply(units:TUnit*) = if (rule.apply(units)) give else Seq.empty
  }

  case class First(rules:Standing*) extends Standing {
    def apply(units:TUnit*) = rules.foldLeft(Seq.empty[TUnit]) { case (s, r) =>
      if (s.isEmpty) r.apply(units:_*) else s
    }
  }

}

package info.tweaked

/**
 * A programme of study
 */
case class Plan(name:String, terms:Termful*) {

  def printCheck() = {
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
        if (!unit.require.apply(buf.toSeq)) {
          probs.append(s"${unit.name} prerequisites have not been met")
        }
      }
      buf.append(termful.units:_*)
    }
    probs.toSeq
  }
}

/** A Term with a selection of Units to take in that Term */
case class Termful(term:Term, units:Seq[Unit])


package au.edu.une.cs.cstweaked

import info.tweaked.Unit
import info.tweaked.{T1, T2, T3}

/**
 * Holds definitions of COSC units
 */
object CoscUnits {

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

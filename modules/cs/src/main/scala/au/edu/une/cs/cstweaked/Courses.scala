package au.edu.une.cs.cstweaked

import info.tweaked.Prerequisite.Contains
import info.tweaked.Standing.{Grant, First}
import info.tweaked.Term._
import info.tweaked._
import info.tweaked.TUnit._
import CoscUnits._
import CompUnits._

object Courses {

  val bcompCore = NamedRule("BCompSc Core",
    Prerequisite.Contains(AMTH140, COSC110, COSC120, COSC210, COSC220, COSC230, COSC240, COSC310, COSC320)
  )

  val bcompMaths = NamedRule("Maths Requirement",
    Prerequisite.NumFrom(2, MTHS120, MTHS130, STAT100),
    extra = Seq(MTHS110),
    notes = Seq("MTHS110 is an elective for students not ready for MTHS120")
  )

  val softMajAlone = NamedRule("Software Development Major",
    Prerequisite.NumFrom(5, COSC250, COSC260, COSC330, COSC340, COSC350, COSC360, COSC370)
  )

  val softMajWithAM = NamedRule("Software Development Major",
    Prerequisite.NumFrom(5, COSC260, COSC340, COSC350, COSC360, COSC370),
    notes=Seq("Although COSC250 and COSC330 are also prescribed units for the Software Development major, in the dual major they count towards Applied Modelling")
  )

  val appMod = NamedRule("Applied Modelling Major",
    Prerequisite.Contains(COSC250, COSC330, COSC380, STAT210, STAT330)
  )

  val twentyFourUnits = NamedRule("Complete 24 units",
    Prerequisite.NumSatisfying(24, _ != TUnit.Unchosen)
  )

  val bCompStanding = Standing.All(
    First(
      Grant(Contains(math101, math102), MTHS120, MTHS130),
      Grant(Contains(math101), MTHS120),
      Grant(Contains(math120), MTHS120),
      Grant(Contains(math102), MTHS130)
    ),
    First(
      Grant(Contains(comp131, comp132, comp170), COSC110, COSC120, TUnit.x("Unspecified 100-level COSC")),
      Grant(Contains(comp131, comp170), COSC110, COSC120),
      Grant(Contains(comp131, comp132), COSC110, COSC120),
      Grant(Contains(comp131), COSC120)
    )
  )

  val bcompColorRule = (u:TUnit) => {
    if (bcompCore.contains(u)) {
      "core"
    } else if (bcompMaths.contains(u)) {
      "maths"
    } else if (appMod.contains(u) && softMajAlone.contains(u)) {
      "sd am"
    } else if (appMod.contains(u)) {
      "am"
    } else if (softMajAlone.contains(u)) {
      "sd"
    } else ""
  }


  val bCompAM = Course("BCompSc (Applied Modelling)",
    Seq(bcompCore, bcompMaths, appMod, twentyFourUnits),
    coloringRule = bcompColorRule, standing = bCompStanding
  )

  val bCompSD = Course("BCompSc (Software Development)",
    Seq(bcompCore, bcompMaths, softMajAlone, twentyFourUnits),
    coloringRule = bcompColorRule, standing = bCompStanding
  )

  val bCompDual = Course("BCompSc (Software Development & Applied Modelling)",
    Seq(bcompCore, bcompMaths, softMajWithAM, appMod, twentyFourUnits),
    coloringRule = bcompColorRule, standing = bCompStanding
  )

}

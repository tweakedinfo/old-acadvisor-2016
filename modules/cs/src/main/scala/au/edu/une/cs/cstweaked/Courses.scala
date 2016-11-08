package au.edu.une.cs.cstweaked

import info.tweaked.model.plan.Prerequisite.Contains
import info.tweaked.model.plan._
import Standing.{Grant, First}
import CoscUnits._
import CompUnits._
import info.tweaked.model.unit.{Term, TeachingUnit}

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
    Prerequisite.NumSatisfying(24, _ != TeachingUnit.Unchosen)
  )

  val bCompStanding = Standing.All(
    First(
      Grant(Contains(math101, math102), MTHS120, MTHS130),
      Grant(Contains(math101), MTHS120),
      Grant(Contains(math120), MTHS120),
      Grant(Contains(math102), MTHS130)
    ),
    First(
      Grant(Contains(comp131, comp132, comp170), COSC110, COSC120, TeachingUnit.x("Unspecified 100-level COSC")),
      Grant(Contains(comp131, comp170), COSC110, COSC120),
      Grant(Contains(comp131, comp132), COSC110, COSC120),
      Grant(Contains(comp131), COSC120)
    )
  )

  val bcompColorRule = (u:TeachingUnit) => {
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


  val bCompSc = Course("BCompSc", "Bachelor of Computer Science",
    rules=Seq(bcompCore, bcompMaths, twentyFourUnits),
    coloringRule = bcompColorRule, standing = bCompStanding,
    majors = Seq(
      Major("Software Development", Seq(softMajAlone)),
      Major("Data Science", Seq(appMod)),
      Major("Software Development & Data Science", Seq(appMod, softMajWithAM))
    )
  )

  val courses = Map(
    "BCompSc" -> bCompSc
  )

  val intakes = Map(
    bCompSc -> Seq(
      Intake(bCompSc, OnCampus, FullTime, Term.t1_2017),
      Intake(bCompSc, OffCampus, FullTime, Term.t1_2017),
      Intake(bCompSc, OnCampus, PartTime, Term.t1_2017),
      Intake(bCompSc, OffCampus, PartTime, Term.t1_2017),
      Intake(bCompSc, OnCampus, FullTime, Term.t2_2017),
      Intake(bCompSc, OffCampus, FullTime, Term.t2_2017),
      Intake(bCompSc, OnCampus, PartTime, Term.t2_2017),
      Intake(bCompSc, OffCampus, PartTime, Term.t2_2017),
      Intake(bCompSc, OffCampus, PartTime, Term.t3_2017)
    )
  )

  val offerings = {
    import Term._

    Map(
      AMTH140 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC101 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC220 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC240 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC260 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC360 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC380 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC350 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC330 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),
      COSC320 -> Seq(t2_2017, t2_2018, t2_2019, t2_2020, t2_2021),

      COSC210 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      COSC230 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      COSC250 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      COSC310 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      COSC340 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      COSC210 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      COSC210 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      STAT210 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),
      STAT330 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021),

      COSC110 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021, t2_2017, t2_2018, t2_2019, t2_2020, t2_2021, t3_2017, t3_2018, t3_2019, t3_2020, t3_2021),
      COSC120 -> Seq(t1_2017, t1_2018, t1_2019, t1_2020, t1_2021, t2_2017, t2_2018, t2_2019, t2_2020, t2_2021)
    )
  }

}

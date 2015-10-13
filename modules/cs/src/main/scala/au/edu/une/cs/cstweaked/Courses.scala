package au.edu.une.cs.cstweaked

import info.tweaked.Term._
import info.tweaked.Plan
import info.tweaked.Unit._
import CoscUnits._

object Courses {

  val plans = Seq(
    Plan("Bachelor of Education (Secondary Science) (Computing Technology Software Design and Mathematics)",
      T1(COSC110, any, any, any),
      T2(COSC120, any, any, any),
      T1(COSC210, any, any, any),
      T2(COSC220, any, any, any),
      T1(COSC370, COSC310)
    ),


    Plan("BEd K12 CITS/M orig",
      T1(COSC110),
      T2(COSC120),
      T1(COSC210),
      T2(COSC240, COSC260),
      T2(COSC360)
    ),

    Plan("BEd K12 CITS/M web",
      T1(COSC110),
      T2(COSC120),
      T1(COSC210),
      T2(COSC240, COSC220),
      T1(),
      T2(COSC260)
    ),

    // SW: COSC260, COSC340, COSC350, COSC360, COSC370
    Plan("BComp SW MTH120",
      T1(COSC110, MTHS120, Elective, Listed),
      T2(AMTH140, COSC120, STAT100, Listed),
      T1(COSC210, COSC230, COSC250, Listed),
      T2(COSC220, COSC240, COSC260, Listed),
      T1(COSC310, COSC340, COSC370, Listed),
      T2(COSC320, COSC350, Listed, COSC360)
    ),


    // Core: COSC110, COSC120, AMTH140, COSC210, COSC220, COSC230, COSC240, COSC310, COSC320
    // SW: COSC260, COSC340, COSC350, COSC360, COSC370
    // AM: COSC250, STAT210, COSC330, COSC380, STAT330

    // Core: COSC110, COSC120, AMTH140, COSC210, COSC220, COSC230, COSC240, COSC310, COSC320
    // SW: COSC260, COSC340, COSC350, COSC360, COSC370
    // AM: COSC250, STAT210, COSC330, COSC380, STAT330
    Plan("BComp SW Am MTH120 b",
      T1(COSC110, MTHS120, Elective, Listed),
      T2(AMTH140, COSC120, STAT100, Listed),
      T1(COSC210, COSC230, COSC250, STAT210),
      T2(COSC220, COSC240, COSC260, COSC350),
      T1(COSC310, COSC370, COSC340, STAT330),
      T2(COSC320, COSC360, COSC380, COSC330)
    ),

    Plan("BComp Dual Major MTH110 c",
      T1(COSC110, MTHS110, Elective, Listed),
      T2(AMTH140, COSC120, STAT100, MTHS121),
      T1(COSC210, COSC230, COSC250, STAT210),
      T2(COSC220, COSC240, COSC260, COSC330),
      T1(COSC310, COSC370, COSC340, STAT330),
      T2(COSC320, COSC360, COSC380, COSC350)
    ),

    Plan("BComp Applied Modelling MTH120 T1 Amendment Sample",
      T1(COSC110, MTHS120, Elective, Listed),
      T2(COSC120, AMTH140, STAT100, Listed),
      T1(COSC210, COSC230, COSC250, STAT210),
      T2(COSC220, COSC240, Listed, Listed),
      T1(COSC310, STAT330, Elective, Listed),
      T2(COSC320, COSC330, COSC380, Listed)
    ),


    Plan("BComp SW MTH120 T1 Amendment Sample",
      T1(COSC110, MTHS120, Elective, Listed),
      T2(COSC120, AMTH140, STAT100, Listed),
      T1(COSC210, COSC230, Prescribed, Listed),
      T2(COSC220, COSC240, Prescribed, Listed),
      T1(COSC310, Prescribed, Elective, Listed),
      T2(COSC320, Prescribed, Prescribed, Listed)
    ),



    Plan("BComp Applied Modelling MTH120 part time T2 Amendment sample",
      T2(COSC110, AMTH140),//1
      T1(COSC120, Elective), T2(STAT100, MTHS121),//2
      T1(COSC210, COSC230), T2(COSC240, Listed),//3
      T1(COSC250, Listed), T2(COSC220, Listed),//4
      T1(STAT210, Listed), T2(COSC380, COSC330),//5
      T1(COSC310, Elective), T2(Listed, COSC320),//6
      T1(STAT330, Listed)//7
    ),




    Plan("BComp Dual Degree MTH120 part time T2 Amendment sample",
      T2(STAT100, AMTH140),//1
      T1(COSC110, Elective), T2(COSC120, MTHS121),//2
      T1(COSC210, COSC230), T2(COSC240, COSC260),//3
      T1(COSC250, STAT210), T2(COSC220, COSC360),//4
      T1(COSC340, COSC370), T2(COSC380, COSC330),//5
      T1(COSC310, Elective), T2(COSC350, COSC320),//6
      T1(STAT330, Listed)//7
    ),


    Plan("BComp SW Am MTH110 T2 start",
      T2(COSC110, MTHS110, AMTH140, STAT100),
      T1(COSC120, MTHS120, Elective, Listed),
      T2(COSC220, COSC240, Listed, Listed),
      T1(COSC210, COSC230, COSC310, STAT210),
      T2(COSC380, COSC330, Listed, Listed),
      T1(Listed, COSC320, COSC250, STAT330)
    ),

    Plan("BComp SW Am MTH110 d",
      T1(COSC110, MTHS120, Elective, Listed),
      T2(AMTH140, COSC120, STAT100, MTHS130),
      T1(COSC210, COSC230, COSC250, STAT210),
      T2(COSC220, COSC240, COSC260, COSC350),
      T1(COSC310, COSC370, COSC340, STAT330),
      T2(COSC320, COSC360, COSC380, COSC330)
    ),



    Plan("BComp SW MTH120 T1 Amendment Sample",
      T1(COSC110, MTHS120, Elective, Listed),
      T2(COSC120, AMTH140, STAT100, Listed),
      T1(COSC210, COSC230, Prescribed, Listed),
      T2(COSC220, COSC240, Prescribed, Listed),
      T1(COSC310, Prescribed, Elective, Listed),
      T2(COSC320, Prescribed, Prescribed, Listed)
    ),


    // Core: COSC110, COSC120, AMTH140, COSC210, COSC220, COSC230, COSC240, COSC310, COSC320
    // SW: COSC250, COSC260, COSC330, COSC340, COSC350, COSC360, COSC370
    // AM: COSC250, STAT210, COSC330, COSC380, STAT330
    Plan("BComp Software T2 start MTH110 ",
      T2(AMTH140, COSC110, STAT100, Elective),
      T1(COSC120, MTHS120, Listed, Elective),
      T2(COSC220, COSC240, Prescribed, Listed),
      T1(COSC210, COSC230, Prescribed, COSC310),
      T2(COSC320, Prescribed, Listed, Listed),
      T1(Listed, Prescribed, Prescribed, Elective)
    ),

    // AM: COSC250, STAT210, COSC330, COSC380, STAT330
    Plan("BComp Applied Modelling T2 start MTH110 ",
      T2(AMTH140, COSC110, STAT100, MTHS110),
      T1(COSC120, MTHS120, STAT210, Elective),
      T2(COSC220, COSC240, Listed, Listed),
      T1(COSC210, COSC230, Prescribed, COSC310),
      T2(COSC320, Listed, Prescribed, Prescribed),
      T1(Listed, Listed, Elective, Prescribed)
    ),


    Plan("BComp Software and Modelling T2 start MTH110 ",
      T2(AMTH140, COSC110, STAT100, MTHS110),
      T1(COSC120, MTHS120, STAT210, Listed),
      T2(COSC220, COSC240, COSC260, COSC380),
      T1(COSC210, COSC230, COSC250, COSC310),
      T2(COSC320, COSC360, COSC350, COSC330),
      T1(Listed, COSC370, COSC340, STAT330)
    ),

    Plan("MIT",
      T1(COSC110, COSC210, COSC310, x("SCI500")),
      T2(COSC120, COSC220, COSC260, COSC350),
      T1(COSC340, COSC370, x("COSC593 or COSC592")),
      T2(COSC330, COSC360, x("COSC593 or COSC592"))
    ),





    Plan("BComp SW",
      T1(),
      T2(),
      T1(),
      T2(),
      T1(),
      T2()
    ),

    Plan("BComp SW",
      T1(),
      T2(),
      T1(),
      T2(),
      T1(),
      T2()
    ),

    Plan("BComp SW",
      T1(),
      T2(),
      T1(),
      T2(),
      T1(),
      T2()
    )

  )







  def main(args:Array[String]) = {
    for (p <- plans) {
      p.printCheck
    }
  }




}

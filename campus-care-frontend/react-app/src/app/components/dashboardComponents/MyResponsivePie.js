import React, { useState, useEffect, useContext } from "react";
import { ResponsivePie } from "@nivo/pie";
import NotificationService from "../../../service/NotificationService";
import CovidInfoService from "../../../service/CovidInfoService";

function MyResponsivePie() {

  const [notAllowed, setNotAllowedStudentNum] = useState();
  const [vaccinated, setVaccinatedStudentNum] = useState();
  const [tested, setTestedStudentNum] = useState();

  async function fetchStatistics(){
    const response1 = await CovidInfoService.getNotAllowedStatistics();
    const response2 = await CovidInfoService.getVaccinatedStatistics();
    const response3 = await CovidInfoService.getTestedStatistics();
    {
      setNotAllowedStudentNum(response1);
      setVaccinatedStudentNum(response2);
      setTestedStudentNum(response3);
    }
  }
  useEffect(async () => {
    console.log(vaccinated)
    await fetchStatistics()
  })
  const data = [
    {
      id: "Not Allowed Students",
      label: "Not Allowed Students",
      value: notAllowed,
      color: "hsl(90, 70%, 50%)",
    },
    {
      id: "Vaccinated Students",
      label: "Vaccinated Students",
      value: vaccinated,
      color: "hsl(56, 70%, 50%)",
    },
    {
      id: "Tested Students",
      label: "Tested Students",
      value: tested,
      color: "hsl(56, 70%, 50%)",
    },
  ];

  return (
    <ResponsivePie
      data={data}
      margin={{ top: 40, right: 80, bottom: 80, left: 80 }}
      innerRadius={0.5}
      padAngle={0.7}
      cornerRadius={3}
      activeOuterRadiusOffset={8}
      colors={{ scheme: "accent" }}
      borderWidth={1}
      borderColor={{ from: "color", modifiers: [["darker", 0.2]] }}
      arcLinkLabelsSkipAngle={10}
      arcLinkLabelsTextColor="#333333"
      arcLinkLabelsThickness={2}
      arcLinkLabelsColor={{ from: "color" }}
      arcLabelsSkipAngle={10}
      arcLabelsTextColor={{ from: "color", modifiers: [["darker", 2]] }}
      defs={[
        {
          id: "dots",
          type: "patternDots",
          background: "inherit",
          color: "rgba(255, 255, 255, 0.3)",
          size: 4,
          padding: 1,
          stagger: true,
        },
        {
          id: "lines",
          type: "patternLines",
          background: "inherit",
          color: "rgba(255, 255, 255, 0.3)",
          rotation: -45,
          lineWidth: 6,
          spacing: 10,
        },
      ]}
      fill={[
        {
          match: {
            id: "ruby",
          },
          id: "dots",
        },
        {
          match: {
            id: "c",
          },
          id: "dots",
        },
        {
          match: {
            id: "go",
          },
          id: "dots",
        },
        {
          match: {
            id: "python",
          },
          id: "dots",
        },
        {
          match: {
            id: "scala",
          },
          id: "lines",
        },
        {
          match: {
            id: "lisp",
          },
          id: "lines",
        },
        {
          match: {
            id: "elixir",
          },
          id: "lines",
        },
        {
          match: {
            id: "javascript",
          },
          id: "lines",
        },
      ]}
      legends={[]}
    />
  );

}
export default MyResponsivePie;

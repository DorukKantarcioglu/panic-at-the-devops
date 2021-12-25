import { ResponsiveLine } from "@nivo/line";
import MyResponsivePie from "./MyResponsivePie";
import {useEffect, useState} from "react";
import CovidInfoService from "../../../service/CovidInfoService";

function MyResponsiveLine() {
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
      id: "covid cases",
      color: "hsl(222, 70%, 50%)",
      data: [
        {
          x: "monday",
          y: 20,
        },
        {
          x: "tuesday",
          y: 12,
        },
        {
          x: "wednesday",
          y: 16,
        },
        {
          x: "thursday",
          y: 10,
        },
        {
          x: "friday",
          y: 17,
        },
        {
          x: "saturday",
          y: 13,
        },
        {
          x: "sunday",
          y: 15,
        },
      ],
    },

  ];
  return (
    <ResponsiveLine
      data={data}
      margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
      xScale={{ type: "point" }}
      yScale={{
        type: "linear",
        min: "auto",
        max: "auto",
        stacked: true,
        reverse: false,
      }}
      yFormat=" >-.2f"
      axisTop={null}
      axisRight={null}
      axisBottom={{
        orient: "bottom",
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: "",
        legendOffset: 36,
        legendPosition: "middle",
      }}
      axisLeft={{
        orient: "left",
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: "count",
        legendOffset: -40,
        legendPosition: "middle",
      }}
      colors={{ scheme: "accent" }}
      pointSize={10}
      pointColor={{ theme: "background" }}
      pointBorderWidth={2}
      pointBorderColor={{ from: "serieColor" }}
      pointLabelYOffset={-12}
      enableArea={true}
      useMesh={true}
      legends={[
        {
          anchor: "bottom-right",
          direction: "column",
          justify: false,
          translateX: 100,
          translateY: 0,
          itemsSpacing: 0,
          itemDirection: "left-to-right",
          itemWidth: 80,
          itemHeight: 20,
          itemOpacity: 0.75,
          symbolSize: 12,
          symbolShape: "circle",
          symbolBorderColor: "rgba(0, 0, 0, .5)",
          effects: [
            {
              on: "hover",
              style: {
                itemBackground: "rgba(0, 0, 0, .03)",
                itemOpacity: 1,
              },
            },
          ],
        },
      ]}
    />
  );
}
export default MyResponsiveLine;

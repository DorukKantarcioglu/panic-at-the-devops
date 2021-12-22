
import { ResponsiveLine } from '@nivo/line'
import MyResponsivePie from './MyResponsivePie'


function MyResponsiveLine(){

   const data= [
        {
          "id": "japan",
          "color": "hsl(222, 70%, 50%)",
          "data": [
            {
              "x": "plane",
              "y": 284
            },
            {
              "x": "helicopter",
              "y": 163
            },
            {
              "x": "boat",
              "y": 97
            },
            {
              "x": "train",
              "y": 201
            },
            {
              "x": "subway",
              "y": 66
            },
            {
              "x": "bus",
              "y": 263
            },
            {
              "x": "car",
              "y": 208
            },
            {
              "x": "moto",
              "y": 88
            },
            {
              "x": "bicycle",
              "y": 148
            },
            {
              "x": "horse",
              "y": 21
            },
            {
              "x": "skateboard",
              "y": 204
            },
            {
              "x": "others",
              "y": 168
            }
          ]
        },
        {
          "id": "france",
          "color": "hsl(3, 70%, 50%)",
          "data": [
            {
              "x": "plane",
              "y": 238
            },
            {
              "x": "helicopter",
              "y": 29
            },
            {
              "x": "boat",
              "y": 98
            },
            {
              "x": "train",
              "y": 224
            },
            {
              "x": "subway",
              "y": 224
            },
            {
              "x": "bus",
              "y": 13
            },
            {
              "x": "car",
              "y": 125
            },
            {
              "x": "moto",
              "y": 262
            },
            {
              "x": "bicycle",
              "y": 232
            },
            {
              "x": "horse",
              "y": 130
            },
            {
              "x": "skateboard",
              "y": 229
            },
            {
              "x": "others",
              "y": 224
            }
          ]
        },
        {
          "id": "us",
          "color": "hsl(315, 70%, 50%)",
          "data": [
            {
              "x": "plane",
              "y": 252
            },
            {
              "x": "helicopter",
              "y": 226
            },
            {
              "x": "boat",
              "y": 26
            },
            {
              "x": "train",
              "y": 249
            },
            {
              "x": "subway",
              "y": 277
            },
            {
              "x": "bus",
              "y": 35
            },
            {
              "x": "car",
              "y": 141
            },
            {
              "x": "moto",
              "y": 216
            },
            {
              "x": "bicycle",
              "y": 287
            },
            {
              "x": "horse",
              "y": 230
            },
            {
              "x": "skateboard",
              "y": 297
            },
            {
              "x": "others",
              "y": 128
            }
          ]
        },
        {
          "id": "germany",
          "color": "hsl(138, 70%, 50%)",
          "data": [
            {
              "x": "plane",
              "y": 112
            },
            {
              "x": "helicopter",
              "y": 184
            },
            {
              "x": "boat",
              "y": 101
            },
            {
              "x": "train",
              "y": 89
            },
            {
              "x": "subway",
              "y": 173
            },
            {
              "x": "bus",
              "y": 244
            },
            {
              "x": "car",
              "y": 216
            },
            {
              "x": "moto",
              "y": 105
            },
            {
              "x": "bicycle",
              "y": 176
            },
            {
              "x": "horse",
              "y": 40
            },
            {
              "x": "skateboard",
              "y": 134
            },
            {
              "x": "others",
              "y": 290
            }
          ]
        },
        {
          "id": "norway",
          "color": "hsl(43, 70%, 50%)",
          "data": [
            {
              "x": "plane",
              "y": 275
            },
            {
              "x": "helicopter",
              "y": 116
            },
            {
              "x": "boat",
              "y": 173
            },
            {
              "x": "train",
              "y": 14
            },
            {
              "x": "subway",
              "y": 279
            },
            {
              "x": "bus",
              "y": 263
            },
            {
              "x": "car",
              "y": 280
            },
            {
              "x": "moto",
              "y": 188
            },
            {
              "x": "bicycle",
              "y": 20
            },
            {
              "x": "horse",
              "y": 273
            },
            {
              "x": "skateboard",
              "y": 277
            },
            {
              "x": "others",
              "y": 270
            }
          ]
        }
      ]
return (
<ResponsiveLine
        data={data}
        margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
        xScale={{ type: 'point' }}
        yScale={{ type: 'linear', min: 'auto', max: 'auto', stacked: true, reverse: false }}
        yFormat=" >-.2f"
        axisTop={null}
        axisRight={null}
        axisBottom={{
            orient: 'bottom',
            tickSize: 5,
            tickPadding: 5,
            tickRotation: 0,
            legend: 'transportation',
            legendOffset: 36,
            legendPosition: 'middle'
        }}
        axisLeft={{
            orient: 'left',
            tickSize: 5,
            tickPadding: 5,
            tickRotation: 0,
            legend: 'count',
            legendOffset: -40,
            legendPosition: 'middle'
        }}
        colors={{ scheme: 'nivo' }}
        pointSize={10}
        pointColor={{ theme: 'background' }}
        pointBorderWidth={2}
        pointBorderColor={{ from: 'serieColor' }}
        pointLabelYOffset={-12}
        enableArea={true}
        areaOpacity={0.5}
        useMesh={true}
        legends={[
            {
                anchor: 'bottom-right',
                direction: 'column',
                justify: false,
                translateX: 100,
                translateY: 0,
                itemsSpacing: 0,
                itemDirection: 'left-to-right',
                itemWidth: 80,
                itemHeight: 20,
                itemOpacity: 0.75,
                symbolSize: 12,
                symbolShape: 'circle',
                symbolBorderColor: 'rgba(0, 0, 0, .5)',
                effects: [
                    {
                        on: 'hover',
                        style: {
                            itemBackground: 'rgba(0, 0, 0, .03)',
                            itemOpacity: 1
                        }
                    }
                ]
            }
        ]}
    />
);
}
export default MyResponsiveLine;
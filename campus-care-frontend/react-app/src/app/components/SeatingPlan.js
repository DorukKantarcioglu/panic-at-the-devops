import React, {useEffect, useState} from "react";
import NewSeatingPlanForm from "./NewSeatingPlanForm";

const SeatingPlan =()=>
{

    const [chart, setChart] = useState([]);
    const [data, setData] = useState([1,2,3,4,5,6,7,8,9,10,11,12]);
    const [rowNo, setRowNo] = useState(2);
    const [columnNo, setColumnNo] = useState(4);
    const [read, setRead] = useState(true);
    const [exist, setExist] = useState(true);

    useEffect(()=>{
        if(exist)
        fetchSeatingPlan();
    },[read, exist])

    const saveStudent = (event) => {
        let seatId = event.target.id;
        let studentId = event.target.value;
        let temp = data;
        temp[seatId] = studentId;
        setData(temp);
    };

    const fetchSeatingPlan=()=>
    {
        let table = [];
        let parent = [];
        for (let i = 1; i <= rowNo; i++)
        {
            let children = [];
            for (let j = 1; j <= columnNo; j++)
            {
                let id = (j-1)+((i-1)*columnNo);

                    children.push(<td>
                        <input type="text" name="seat" id = {id}
                               onChange={saveStudent} readOnly={read}  defaultValue={data[id]} >

                        </input> </td>);
            }
            parent.push(<tr> {children} </tr>);
        }
        table.push(<table>{parent}</table>)

        setChart(table);
    }
    const save=()=>
    {
        setExist(true);

    }

    const updateChart=()=>{
        setRead(false);
    }

    const handleChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };


        return(
            <div className="seatingPlanForm">
            {exist?(<div>
                    {chart}
                    <button id = "seatingPlanUpdate" onClick={updateChart}> Update the seating plan </button>
                    </div>
                ):
                    (
                        <NewSeatingPlanForm onSave = {save}/>

                    )
            }
            </div>
        )
}

export default SeatingPlan;
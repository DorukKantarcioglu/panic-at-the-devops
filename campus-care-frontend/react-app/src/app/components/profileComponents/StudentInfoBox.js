import React from "react";


const StudentInfoBox =(props)=>
{
    var riskStatus
    if (props.risky)
        riskStatus = "RISKY!\n NOT ALLOWED ON CAMPUS"
    else
        riskStatus = "ALLOWED ON CAMPUS"

    if (props.clicked)
    var infoBox =
        <>
            <br/><br/>
            <label> Student with id {props.id} </label>
            <br/> <br/>
            <label> Risk Status:  {riskStatus} </label>
            <br/> <br/>
            <label> Date: {props.date} </label> <br/>
            <label> Time:  {props.time} </label> <br/>
            <label> Place: {props.place} </label>
        </>
    else
        var infoBox = <></>
    return infoBox;
}
export default StudentInfoBox;


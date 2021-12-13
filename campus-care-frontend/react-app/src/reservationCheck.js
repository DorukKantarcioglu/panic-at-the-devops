import React, {useState} from "react";
import "./LoginFormStyle.css";
import StudentService from "./StudentService";


const ReservationCheck =()=>
{

    const [studentId, setStudentId] = useState('')
    const [info, setInfo] = useState('')


    const handleChange = (event) => {
        setStudentId( event.target.value);
    };


    const getData =async () => {

        console.log(studentId)

        const students = await StudentService.getStudentById(studentId);
        const hesCode = students[0].hesCode.toString();
        console.log(hesCode)
       const response = await StudentService.validateHesCode(hesCode, "25856242442", "Ge2212690")
        const split = response.toString().split("\n")
        setInfo(split);

    }

        const studentIdForm =
            <>
                <label></label>
                <input type="text" id="studentId" name="studentId" placeholder="student ID"
                         onChange={() => handleChange}  />
                <button  value="Check Reservation" onClick={getData} >Check Reservation</button>
                <div>
            </div>
                </>

        return (
                <div>
                    <div>{studentIdForm}</div>
                    <div>{info}</div>


                </div>
        );

}

export default ReservationCheck;

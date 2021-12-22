import React, { useState } from "react";
import StudentService from "../../service/StudentService";

const ReservationCheck = () => {
  const [studentId, setStudentId] = useState("");
  const [info, setInfo] = useState("");

  const handleChange = (event) => {
    setStudentId(event.target.value);
  };

  const getData = async () => {
    console.log(studentId);

    const students = await StudentService.getStudentById(studentId);
    const hesCode = students[0].hesCode.toString();
    console.log(hesCode);
    const response = await StudentService.validateHesCode(
      hesCode,
      "25856242442",
      "Ge2212690"
    );
    const split = response.toString().split("\n");
    setInfo(split);
  };

  const studentIdForm = (
    <div>
      <label>Enter your id: </label>
      <input
        className="m-2"
        type="text"
        id="studentId"
        name="studentId"
        placeholder="student ID"
        onChange={() => handleChange}
      />
      <div
        className="button m-2 p-2"
        value="Check Reservation"
        onClick={getData}
      >
        Check Reservation
      </div>
    </div>
  );

  return (
    <div>
      <div>{studentIdForm}</div>
      <div>{info}</div>
    </div>
  );
};

export default ReservationCheck;

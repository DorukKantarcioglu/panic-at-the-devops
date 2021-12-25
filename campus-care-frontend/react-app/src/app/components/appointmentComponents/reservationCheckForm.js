import React, { useState } from "react";
import StudentService from "../../../service/StudentService";
import { Form, Button } from "react-bootstrap";

const ReservationCheck = () => {
  const [studentId, setStudentId] = useState("");
  const [info, setInfo] = useState("");
  const [show, setShow] = useState(false);

  const handleChange = (event) => {
    setStudentId(event.target.value);
  };
  const handleClose = () => {
    setShow(false);
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
      <Form>
        <div className="row d-flex border ">
          <div className="col-8">
            <label className="" style={{ color: "indigo200" }}>
              The student's id:
            </label>
            <input
              type="text"
              id="studentId"
              name="studentId"
              placeholder="student ID"
              onChange={() => handleChange}
            ></input>
            <div className="ms-2">
              <button
                className="button mt-3 p-2"
                value="Check Reservation"
                onClick={getData}
              >
                Check Reservation
              </button>
              <button
                className="button mt-3 ms-4 p-2"
                variant="secondary"
                onClick={handleClose}
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </Form>
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

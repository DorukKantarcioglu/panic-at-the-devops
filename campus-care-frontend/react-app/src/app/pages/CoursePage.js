import React, { useEffect, useState } from "react";
import { ModalBody } from "react-bootstrap";
import Modal from "antd/es/modal/Modal";
import StudentService from "../../service/StudentService";
import StudentList from "../components/profileComponents/StudentList";
import SeatingPlan from "../components/SeatingPlan";
import Button from "react-bootstrap/Button";
import CourseService from "../../service/CourseService";


const CoursePage = (props) => {
  const [data, setData] = useState([]);
  const [seatingPlan, setSeatingPlan] = useState();

  const [showSeating, setSeating] = useState(false);

  useEffect(() => {
    if (seatingPlan == null) fetchData().then();
    console.log(seatingPlan);
  }, [seatingPlan]);

  const fetchData = async () => {
    setData(await StudentService.fetchAllStudents().then());
    setSeatingPlan(await CourseService.getSeatingPlan(props.courseCode).then());
  };

  const showSeatingPlan = () => {
    setSeating(true);
  };

  const closeSeatingPlan = () => {
    setSeating(false);
  };

  return (
    <div>
      <>{props.courseCode}</>
      <SeatingPlan
        show={showSeating}
        seatingPlan={seatingPlan}
        courseCode={props.courseCode}
        handleClose={closeSeatingPlan}
      />
      <StudentList data={data} courseCode={props.courseCode}/>
      <button className="button m-2" onClick={showSeatingPlan}>
        Seating Plan{" "}
      </button>
    </div>
  );
};

export default CoursePage;


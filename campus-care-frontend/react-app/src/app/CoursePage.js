import StudentService from "../service/StudentService";
import StudentList from "./components/profileComponents/StudentList";
import { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {Container, Col, Row} from 'react-bootstrap';

const CoursePage = () => {
  const [data, setData] = useState([]);
  const data2 = [{ name: "Elif" }, { name: "Ece" }, { name: "Elif" }];

  const fetchData = async () => {
    setData(await StudentService.fetchAllStudents());
  };

  useEffect(() => {
    console.log("useEff");
    fetchData().then();
  }, []);

  return (
    <Container>
        <Row>
            <StudentList data={data} />
            sheesh
        </Row>
        <Row>
            <i
                className="fas fa-arrow-right"
                style={{ fontSize: "20pt", color: "#29377e" }}
            ></i>
            <FontAwesomeIcon icon="check-square"> yağmur </FontAwesomeIcon>
        </Row>

    </Container>
  );
};

export default CoursePage;

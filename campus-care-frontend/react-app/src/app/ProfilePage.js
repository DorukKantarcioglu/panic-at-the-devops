import React from "react";
import { BrowserRouter as Router, Link, Route, Switch } from "react-router-dom";
import InfoBox from "./components/profileComponents/InfoBox";
import { Container, Col, Row } from "react-bootstrap";
import StudentList from "./components/profileComponents/StudentList";
import CoursePage from "./CoursePage";
import SideBar from "./components/SideBar";

class MyProfile extends React.Component {
  constructor() {
    super();
    this.state = {
      name: "Elif",
      surname: "Cenesiz",
      id: 21902461,
      hesCode: "XXX",
    };
  }

  render() {
    return (
      <Container>
        <div className="col4">
          <SideBar></SideBar>
        </div>
        <Row>
          <InfoBox />
        </Row>
        <Row></Row>
        <Row>Appointments (To-do)</Row>
      </Container>
    );
  }
}
export default MyProfile;

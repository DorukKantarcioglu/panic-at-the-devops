import React from "react";
import InfoBox from "../components/profileComponents/InfoBox";
import { Container, Col, Row } from "react-bootstrap";
import SideBar from "../components/SideBar/SideBar";
import ReservationsList from "../components/appointmentComponents/ReservationsList";
import ReservationService from "../../service/ReservationService";
import AppointmentPage from "./AppointmentPage";

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
        <Row>
            <div className="col-md-4" style={{ marginTop: "-133px",  marginLeft: "-330px" }}>
                <SideBar/>
            </div>
            <Col md = {8}>
                <Container>
                    <Row>
                        <InfoBox style={{ marginTop: "-200px",  marginLeft: "-200px",  width: "100%"}}/>
                    </Row>
                    <br/>
                    <Row>
                        <AppointmentPage hidden ={true}/>
                    </Row>
                </Container>

            </Col>
        </Row>
      </Container>
    );
  }
}
export default MyProfile;

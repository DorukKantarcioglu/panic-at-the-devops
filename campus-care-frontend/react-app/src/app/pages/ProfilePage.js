import React from "react";
import InfoBox from "../components/profileComponents/InfoBox";
import { Container, Col, Row } from "react-bootstrap";
import SideBar from "../components/SideBar/SideBar";
import ReservationsList from "../components/appointmentComponents/ReservationsList";

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
            <Col md = {4}>
                <SideBar/>
            </Col>
            <Col md = {8}>
                <Container>
                    <Row>
                        <InfoBox/>
                    </Row>
                    <br/>
                    <Row>
                        <ReservationsList/>
                    </Row>
                </Container>

            </Col>
        </Row>
      </Container>
    );
  }
}
export default MyProfile;

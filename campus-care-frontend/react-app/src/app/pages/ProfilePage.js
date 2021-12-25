import React from "react";
import InfoBox from "../components/profileComponents/InfoBox";
import { Container, Col, Row } from "react-bootstrap";
import SideBar from "../components/SideBar/SideBar";
import ReservationsList from "../components/appointmentComponents/ReservationsList";

class MyProfile extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      surname: "",
      id: '',
      hesCode: "",
    };
  }

  render() {
    return (
      <Container>
        <Row>
            <Col md = {4}>
                <SideBar id ={this.props.id} />
            </Col>
            <Col md = {8}>
                <Container>
                    <Row>
                        <InfoBox id ={this.props.id}/>
                    </Row>
                    <br/>
                    <Row>

                    </Row>
                </Container>

            </Col>
        </Row>
      </Container>
    );
  }
}
export default MyProfile;

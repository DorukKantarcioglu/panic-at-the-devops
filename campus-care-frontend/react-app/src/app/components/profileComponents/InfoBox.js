import React from "react";
import StudentService from "../../../service/StudentService";
import { Container, Col, Row } from "react-bootstrap";
import LocalStorageService from "../../../service/LocalStorageService";
import InstructorService from "../../../service/InstructorService";

class InfoBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      student: {
        id: null,
        name: null,
        email: null,
        hesCode: null,
        allowedOnCampus: null,
        vaccinated: null,
        tested: null,
        newHesCode: "",
      },
    };
  }

  handleChange = (event) => {
    this.setState({ newHesCode: event.target.value });
  };

  changeHesCode = async () => {
    await StudentService.updateHesCode(
      this.state.student.id,
      this.state.newHesCode
    );
  };


  async componentDidMount() {


    let student = await InstructorService.getInstructorById(LocalStorageService.getId())

    this.setState({
      student: student,
    });

  }

  render() {

    return (
      <Container>
        <Row>
          <Col md = {4}>
            <div
              className="infoBox"
              style={{ borderStyle: "dashed",  }}
            >
              <label className="infoBox">
                {" "}
                Name: {this.state.student.name}{" "}
              </label>
              <br />
              <label className="infoBox">
                {" "}
                Email: {this.state.student.email}{" "}
              </label>
              <br />
              <label className="infoBox">
                {" "}
                Hes Code: {this.state.student.hesCode}{" "}
              </label>
              <br />
              <label className="infoBox">
                {" "}
                isAllowed: {this.state.student.allowedOnCampus}{" "}
              </label>
              <br />
              <label className="infoBox">
                {" "}
                vaccinated: {this.state.student.vaccinated}{" "}
              </label>
              <br />
            </div>
          </Col>
          <Col md = {8}>
            <div className="updateHesCode" style={{ width: "auto" }}>
              <label className="m-2"> Enter your recent HES code: </label>
              <input
                type="text"
                id="hesCode"
                name="hesCode"
                onChange={this.handleChange}
              />
              <button className="button m-2" onClick={this.changeHesCode}>
                {" "}
                Update{" "}
              </button>
            </div>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default InfoBox;

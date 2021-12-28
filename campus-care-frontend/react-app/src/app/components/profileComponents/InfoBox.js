import React from "react";
import StudentService from "../../../service/StudentService";
import { Container, Col, Row } from "react-bootstrap";
import LocalStorageService from "../../../service/LocalStorageService";
import InstructorService from "../../../service/InstructorService";
import NewSeatingPlanForm from "./NewSeatingPlanForm";

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
      },
      newHesCode:''
    };
  }

  handleChange = (event) => {
    console.log(event.target.value)
    this.setState({ newHesCode: event.target.value });

  };

  changeHesCode = async () => {
    console.log(this.state.student.id)
    console.log(this.state.newHesCode)
    let user = await StudentService.updateHesCode(
      this.state.student.id,
      this.state.newHesCode
    );
    this.setState({
      student: user,
    });
  };

  async componentDidMount() {

 try{
   let user = await InstructorService.getInstructorById(LocalStorageService.getId())
   this.setState({
     student: user,
   });
 } catch (e){
   let user = await StudentService.getStudentById(LocalStorageService.getId())
   this.setState({
     student: user,
   });
 }

  }
  render() {

    return (
      <Container>
        <Row>
          <Col md = {4}>
            <div
              className="infoBox"
              style={{ borderStyle: "dashed", width :"700px"}}
            >
              <label className="infoBox" style = {{fontWeight: "bold"}}>
                {" "}
                Name: {" "}
              </label><br />
              <label>{this.state.student.name}</label>
              <br />
              <label className="infoBox" style = {{fontWeight: "bold"}}>
                {" "}
                Email: {" "}
              </label><br />
              <label>{this.state.student.email}</label>
              <br />
              <label className="infoBox" style = {{fontWeight: "bold"}}>
                {" "}
                Hes Code: {" "}
              </label><br />
              <label>{this.state.student.hesCode}</label>
              <br />
              <label className="infoBox" style = {{fontWeight: "bold"}}>
                {" "}
                Allowed on campus: {" "}
              </label><br />
              <label>{this.state.student.allowedOnCampus?("yes"):("no")}</label>
              <br />
              <label className="infoBox" style = {{fontWeight: "bold"}}>
                {" "}
                Vaccinated: {" "}
              </label><br />
              {this.state.student.vaccinated?("yes"):("no")}
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
                onChange={e => this.handleChange(e)}
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
